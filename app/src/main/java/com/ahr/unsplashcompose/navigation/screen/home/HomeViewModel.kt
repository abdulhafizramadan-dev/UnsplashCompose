package com.ahr.unsplashcompose.navigation.screen.home

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.ahr.unsplashcompose.data.repository.UnsplashImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    unsplashImageRepository: UnsplashImageRepository
) : ViewModel() {
    val getAllImages = unsplashImageRepository.getLatestImages()
}