package com.ahr.unsplashcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahr.unsplashcompose.data.local.entity.UnsplashImageRemoteKeys

@Dao
interface UnsplashImageRemoteKeysDao {

    @Query("SELECT * FROM unsplash_remote_keys_table WHERE id=:id")
    suspend fun getRemoteKeys(id: String): UnsplashImageRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(remoteKeys: List<UnsplashImageRemoteKeys>)

    @Query("DELETE FROM unsplash_remote_keys_table")
    suspend fun deleteRemoteKeys()
}