package com.ahr.unsplashcompose.data.local.entity

import androidx.room.Embedded
import com.ahr.unsplashcompose.domain.data.User

data class UserEntity(

    @Embedded
    val userLinks: UserLinksEntity,

    val username: String = ""
)

fun UserEntity.asDomain(): User =
    User(userLinks = userLinks.asDomain(), username = username)