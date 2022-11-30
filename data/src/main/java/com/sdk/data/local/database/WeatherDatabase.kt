package com.sdk.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sdk.data.local.entity.LocationNameEntity

@Database(entities = [LocationNameEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val dao: LocationNameDao
}