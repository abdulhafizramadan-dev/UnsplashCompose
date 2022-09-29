package com.ahr.unsplashcompose.data.network.response

import com.ahr.unsplashcompose.data.local.entity.UnsplashImageEntity
import com.google.gson.annotations.SerializedName


data class UnsplashImageResponse(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("likes")
	val likes: Int? = null,

	@field:SerializedName("user")
	val user: UserItemResponse? = null,

	@field:SerializedName("urls")
	val urls: UrlsItemResponse? = null
)

fun UnsplashImageResponse.asEntity(): UnsplashImageEntity =
	UnsplashImageEntity(
		id = id ?: "",
		likes = likes ?: 0,
		user = user.asEntity(),
		urls = urls.asEntity()
	)