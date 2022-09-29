package com.ahr.unsplashcompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahr.unsplashcompose.data.local.database.UnsplashDatabase
import com.ahr.unsplashcompose.data.local.entity.UnsplashImageEntity
import com.ahr.unsplashcompose.data.network.service.UnsplashService
import com.ahr.unsplashcompose.data.paging.UnsplashImageRemoteMediator
import com.ahr.unsplashcompose.util.Constant.ITEMS_PER_PAGE
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
@ViewModelScoped
class UnsplashImageRepository @Inject constructor(
    private val unsplashService: UnsplashService,
    private val unsplashDatabase: UnsplashDatabase
) {
    fun getLatestImages(): Flow<PagingData<UnsplashImageEntity>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashImageRemoteMediator(
                unsplashService = unsplashService,
                unsplashDatabase = unsplashDatabase
            )
        ) {
            unsplashDatabase.unsplashImageDao().getAllImages()
        }.flow
    }
}