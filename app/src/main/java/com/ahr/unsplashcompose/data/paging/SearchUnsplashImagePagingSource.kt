package com.ahr.unsplashcompose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahr.unsplashcompose.data.network.response.UnsplashImageResponse
import com.ahr.unsplashcompose.data.network.service.UnsplashService
import com.ahr.unsplashcompose.util.Constant.ITEMS_PER_PAGE

class SearchUnsplashImagePagingSource(
    private val unsplashService: UnsplashService,
    private val query: String
) : PagingSource<Int, UnsplashImageResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImageResponse> {
        val currentPage = params.key ?: 1
        return try {

            val response = unsplashService.searchPhotos(query = query, page = currentPage, perPage = ITEMS_PER_PAGE)
            val data = response.results

            val endOfPaginationReached = data.isEmpty()

            val prevKey = if (currentPage == 1) null else currentPage - 1
            val nextKey = if (endOfPaginationReached) null else currentPage + 1

            if (data.isNotEmpty()) {
                LoadResult.Page(
                    data = data,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashImageResponse>): Int? {
        return state.anchorPosition
    }
}