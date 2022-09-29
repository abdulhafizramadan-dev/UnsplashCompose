package com.ahr.unsplashcompose.data.local.entity

import com.ahr.unsplashcompose.domain.data.UserLinks

data class UserLinksEntity(

    val html: String = ""
)

fun UserLinksEntity.asDomain(): UserLinks =
    UserLinks(html = html)