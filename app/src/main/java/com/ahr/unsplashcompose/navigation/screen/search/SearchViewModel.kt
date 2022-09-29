package com.ahr.unsplashcompose.navigation.screen.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ahr.unsplashcompose.data.repository.UnsplashImageRepository
import com.ahr.unsplashcompose.domain.data.UnsplashImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val unsplashImageRepository: UnsplashImageRepository
) : ViewModel() {

    var searchQuery = mutableStateOf("")

    private val _searchedImages: MutableStateFlow<PagingData<UnsplashImage>> = MutableStateFlow(PagingData.empty())
    val searchedImages: StateFlow<PagingData<UnsplashImage>> get() = _searchedImages.asStateFlow()

    fun searchImages(query: String) {
        viewModelScope.launch {
            unsplashImageRepository.searchImages(query).cachedIn(viewModelScope).collect { pagingData ->
                _searchedImages.value = pagingData
            }
        }
    }
}