package com.ahr.unsplashcompose.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahr.unsplashcompose.domain.data.UnsplashImage
import com.ahr.unsplashcompose.util.Constant.UNSPLASH_IMAGE_TABLE

@Entity(tableName = UNSPLASH_IMAGE_TABLE)
data class UnsplashImageEntity(

	@PrimaryKey(autoGenerate = false)
	val id: String = "",

	val likes: Int = 0,

	@Embedded
	val user: UserEntity,

	@Embedded
	val urls: UrlsEntity
)

fun UnsplashImageEntity.asDomain(): UnsplashImage =
	UnsplashImage(
		id = id,
		likes = likes,
		user = user.asDomain(),
		urls = urls.asDomain()
	)