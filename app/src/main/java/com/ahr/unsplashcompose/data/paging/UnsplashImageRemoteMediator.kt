package com.ahr.unsplashcompose.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ahr.unsplashcompose.data.local.database.UnsplashDatabase
import com.ahr.unsplashcompose.data.local.entity.UnsplashImageEntity
import com.ahr.unsplashcompose.data.local.entity.UnsplashImageRemoteKeys
import com.ahr.unsplashcompose.data.network.response.asEntity
import com.ahr.unsplashcompose.data.network.service.UnsplashService
import com.ahr.unsplashcompose.util.Constant.ITEMS_PER_PAGE

@ExperimentalPagingApi
class UnsplashImageRemoteMediator constructor(
    private val unsplashService: UnsplashService,
    private val unsplashDatabase: UnsplashDatabase
) : RemoteMediator<Int, UnsplashImageEntity>() {

    private val unsplashImageDao = unsplashDatabase.unsplashImageDao()
    private val unsplashImageRemoteKeysDao = unsplashDatabase.unsplashImageRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashImageEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    if (remoteKeys?.prevKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    }
                    remoteKeys.prevKey
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    if (remoteKeys?.nextKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    }
                    remoteKeys.nextKey
                }
            }

            val response = unsplashService.getLatestImages(page = currentPage, perPage = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.isEmpty()

            val prevKey = if (currentPage == 1) null else currentPage - 1
            val nextKey = if (endOfPaginationReached) null else currentPage + 1

            unsplashDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    unsplashImageDao.deleteImages()
                    unsplashImageRemoteKeysDao.deleteRemoteKeys()
                }

                val responseEntity = response.map { it.asEntity() }
                val remoteKeys = responseEntity.map { unsplashEntity ->
                    UnsplashImageRemoteKeys(
                        id = unsplashEntity.id,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }

                unsplashImageDao.insertImages(responseEntity)
                unsplashImageRemoteKeysDao.insertRemoteKeys(remoteKeys)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UnsplashImageEntity>
    ): UnsplashImageRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                unsplashImageRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UnsplashImageEntity>
    ): UnsplashImageRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { unsplashImage ->
            unsplashImageRemoteKeysDao.getRemoteKeys(unsplashImage.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UnsplashImageEntity>
    ): UnsplashImageRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { unsplashImage ->
            unsplashImageRemoteKeysDao.getRemoteKeys(unsplashImage.id)
        }
    }
}