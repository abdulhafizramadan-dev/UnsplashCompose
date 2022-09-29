package com.ahr.unsplashcompose.data.local.entity

import com.ahr.unsplashcompose.domain.data.Urls

data class UrlsEntity(

    val regular: String = ""
)

fun UrlsEntity.asDomain(): Urls =
    Urls(regular = regular)