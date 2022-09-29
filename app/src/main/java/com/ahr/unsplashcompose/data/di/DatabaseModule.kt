package com.ahr.unsplashcompose.data.di

import android.content.Context
import androidx.room.Room
import com.ahr.unsplashcompose.data.local.database.UnsplashDatabase
import com.ahr.unsplashcompose.util.Constant.UNSPLASH_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideUnsplashDatabase(@ApplicationContext context: Context): UnsplashDatabase {
        return Room.databaseBuilder(
            context,
            UnsplashDatabase::class.java,
            UNSPLASH_DATABASE_NAME
        ).build()
    }

}