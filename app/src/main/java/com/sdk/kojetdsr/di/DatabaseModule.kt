package com.sdk.kojetdsr.di

import android.content.Context
import androidx.room.Room
import com.sdk.data.local.database.FavoriteDao
import com.sdk.data.local.database.LocationNameDao
import com.sdk.data.local.database.WeatherDatabase
import com.sdk.data.local.manager.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "Weather.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideLocationNameDao(database: WeatherDatabase): LocationNameDao {
        return database.dao
    }
    @Singleton
    @Provides
    fun provideFavoriteDao(database: WeatherDatabase): FavoriteDao {
        return database.favoriteDao
    }

    @Singleton
    @Provides
    fun provideDataStoreManager(
        @ApplicationContext context: Context
    ): DataStoreManager {
        return DataStoreManager(context)
    }
}