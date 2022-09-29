package com.ahr.unsplashcompose.data.network.response

import com.ahr.unsplashcompose.data.local.entity.UrlsEntity
import com.google.gson.annotations.SerializedName

data class UrlsItemResponse(

    @field:SerializedName("regular")
    val regular: String? = null
)

fun UrlsItemResponse?.asEntity(): UrlsEntity =
    if (this != null) UrlsEntity(regular = regular ?: "") else UrlsEntity()