package com.sdk.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sdk.data.local.entity.FavoriteLocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteName(favoriteLocationEntity: FavoriteLocationEntity)

    @Delete
    suspend fun deleteFavoriteName(favoriteLocationEntity: FavoriteLocationEntity)

    @Query("SELECT * FROM FavoriteTable ORDER BY id DESC")
    fun getAllFavoriteLocations(): Flow<List<FavoriteLocationEntity>>
}