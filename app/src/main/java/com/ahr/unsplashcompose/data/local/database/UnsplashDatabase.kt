package com.ahr.unsplashcompose.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahr.unsplashcompose.data.local.dao.UnsplashImageDao
import com.ahr.unsplashcompose.data.local.dao.UnsplashImageRemoteKeysDao
import com.ahr.unsplashcompose.data.local.entity.UnsplashImageEntity
import com.ahr.unsplashcompose.data.local.entity.UnsplashRemoteKeys

@Database(entities = [UnsplashImageEntity::class, UnsplashRemoteKeys::class], version = 1)
abstract class UnsplashDatabase : RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashImageRemoteKeysDao(): UnsplashImageRemoteKeysDao

}