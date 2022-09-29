package com.ahr.unsplashcompose.data.repository

import androidx.paging.*
import com.ahr.unsplashcompose.data.local.database.UnsplashDatabase
import com.ahr.unsplashcompose.data.local.entity.asDomain
import com.ahr.unsplashcompose.data.network.service.UnsplashService
import com.ahr.unsplashcompose.data.paging.UnsplashImageRemoteMediator
import com.ahr.unsplashcompose.domain.data.UnsplashImage
import com.ahr.unsplashcompose.util.Constant.ITEMS_PER_PAGE
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
@ViewModelScoped
class UnsplashImageRepository @Inject constructor(
    private val unsplashService: UnsplashService,
    private val unsplashDatabase: UnsplashDatabase
) {
    fun getLatestImages(): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashImageRemoteMediator(
                unsplashService = unsplashService,
                unsplashDatabase = unsplashDatabase
            )
        ) {
            unsplashDatabase.unsplashImageDao().getAllImages()
        }.flow
            .map { pagingSource ->
                pagingSource.map { it.asDomain() }
            }
    }
}