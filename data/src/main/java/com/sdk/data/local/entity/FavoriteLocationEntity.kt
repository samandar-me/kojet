package com.sdk.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteTable")
data class FavoriteLocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)