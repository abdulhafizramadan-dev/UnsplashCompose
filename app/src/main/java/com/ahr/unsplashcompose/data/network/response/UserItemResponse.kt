package com.ahr.unsplashcompose.data.network.response

import com.ahr.unsplashcompose.data.local.entity.UserEntity
import com.ahr.unsplashcompose.data.local.entity.UserLinksEntity
import com.ahr.unsplashcompose.domain.data.User
import com.ahr.unsplashcompose.domain.data.UserLinks
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
    } else UserEntity(userLinks = UserLinksEntity())

fun UserItemResponse?.asDomain(): User =
    if (this != null) {
        User(
            userLinks = userLinks.asDomain(),
            username = username ?: ""
        )
    } else User(userLinks = UserLinks())