package com.sdk.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LocationName")
data class LocationNameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "locationName")
    val name: String,
    @ColumnInfo(name = "isLocationSaved")
    val isSaved: Boolean = false
)