package com.ahr.unsplashcompose.data.network.response

import com.ahr.unsplashcompose.data.local.entity.UserEntity
import com.google.gson.annotations.SerializedName

data class UserItemResponse(

    @field:SerializedName("links")
    val userLinks: UserLinksItemResponse? = null,

    @field:SerializedName("username")
    val username: String? = null
)

fun UserItemResponse?.asEntity(): UserEntity =
    if (this != null) {
        UserEntity(
            userLinks = userLinks.asEntity(),
            username = username ?: ""
        )
    } else UserEntity()