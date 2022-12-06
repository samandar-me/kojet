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

    @Query("DELETE FROM FavoriteTable WHERE name = :favName")
    suspend fun deleteByName(favName: String)

    @Query("SELECT * FROM FavoriteTable ORDER BY id DESC")
    fun getAllFavoriteLocations(): Flow<List<FavoriteLocationEntity>>
}