package com.ahr.unsplashcompose.domain.data

data class UnsplashImage(

	val id: String,

	val likes: Int,

	val user: User,

	val urls: Urls
)
