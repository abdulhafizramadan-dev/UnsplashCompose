package com.ahr.unsplashcompose.data.network.response

import com.ahr.unsplashcompose.data.local.entity.UserLinksEntity
import com.ahr.unsplashcompose.domain.data.UserLinks
import com.google.gson.annotations.SerializedName

data class UserLinksItemResponse(

    @field:SerializedName("html")
    val html: String? = null
)

fun UserLinksItemResponse?.asEntity(): UserLinksEntity =
    if (this != null) UserLinksEntity(html = html ?: "") else UserLinksEntity()

fun UserLinksItemResponse?.asDomain(): UserLinks =
    if (this != null) UserLinks(html = html ?: "") else UserLinks()