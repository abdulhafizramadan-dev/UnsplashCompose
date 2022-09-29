package com.ahr.unsplashcompose.data.network.service

import com.ahr.unsplashcompose.BuildConfig
import com.ahr.unsplashcompose.data.network.response.SearchUnsplashImageResponse
import com.ahr.unsplashcompose.data.network.response.UnsplashImageResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashService {

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")
    suspend fun getLatestImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<UnsplashImageResponse>

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos")
    suspend fun searchPhotos(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): SearchUnsplashImageResponse

}