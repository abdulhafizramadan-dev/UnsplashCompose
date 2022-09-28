package com.ahr.unsplashcompose.data.network.response

import com.google.gson.annotations.SerializedName

data class SearchUnsplashImageResponse(
    val total: Int,
    @get:SerializedName("total_pages")
    val totalPages: Int,
    val results: List<UnsplashImageResponse>
)
