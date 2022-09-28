package com.ahr.unsplashcompose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahr.unsplashcompose.util.Constant.UNSPLASH_IMAGE_TABLE

@Entity(tableName = UNSPLASH_IMAGE_TABLE)
data class UnsplashImageEntity(

	@PrimaryKey(autoGenerate = false)
	val id: String,

	val likes: Int,

	@Embedded
	val user: User,

	@Embedded
	val urls: Urls
)

data class User(

	@Embedded
	@ColumnInfo(name = "links")
	val userLinks: UserLinks,

	val username: String
)

data class UserLinks(

	val html: String
)

data class Urls(

	val regular: String
)
