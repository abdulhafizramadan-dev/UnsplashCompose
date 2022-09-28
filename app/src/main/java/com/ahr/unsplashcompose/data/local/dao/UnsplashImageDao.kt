package com.ahr.unsplashcompose.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahr.unsplashcompose.data.local.entity.UnsplashImageEntity

@Dao
interface UnsplashImageDao {

    @Query("SELECT * FROM unsplash_image_table")
    fun getAllImages(): PagingSource<Int, UnsplashImageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<UnsplashImageEntity>)

    @Query("DELETE FROM unsplash_image_table")
    suspend fun deleteImages()
}