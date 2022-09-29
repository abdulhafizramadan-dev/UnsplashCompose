package com.ahr.unsplashcompose.navigation.screen.search

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ahr.unsplashcompose.domain.data.UnsplashImage
import com.ahr.unsplashcompose.navigation.component.ListContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalPagingApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    var searchQuery: String by searchViewModel.searchQuery
    val searchedImages: LazyPagingItems<UnsplashImage> =
        searchViewModel.searchedImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                onTextChanged = { query ->
                    searchQuery = query
                },
                onSearchClicked = { query ->
                    searchViewModel.searchImages(query)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        ListContent(items = searchedImages)
    }
}
