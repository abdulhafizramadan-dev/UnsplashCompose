package com.ahr.unsplashcompose.data.network.response

import com.ahr.unsplashcompose.data.local.entity.UserLinksEntity
import com.google.gson.annotations.SerializedName

data class UserLinksItemResponse(

    @field:SerializedName("html")
    val html: String? = null
)

fun UserLinksItemResponse?.asEntity(): UserLinksEntity =
    if (this != null) UserLinksEntity(html = html ?: "") else UserLinksEntity()