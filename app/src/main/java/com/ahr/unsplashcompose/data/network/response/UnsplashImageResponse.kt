package com.ahr.unsplashcompose.data.network.response

import com.google.gson.annotations.SerializedName


data class UnsplashImageResponse(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("likes")
	val likes: Int? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("urls")
	val urls: Urls? = null
)

data class User(

	@field:SerializedName("links")
	val userLinks: UserLinks? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class UserLinks(

	@field:SerializedName("html")
	val html: String? = null
)

data class Urls(

	@field:SerializedName("regular")
	val regular: String? = null
)
