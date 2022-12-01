package com.sdk.data.local.database

import androidx.room.*
import com.sdk.data.local.entity.LocationNameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationNameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLocationName(name: LocationNameEntity)

    @Delete
    suspend fun deleteLocationName(name: LocationNameEntity)

//    @Query("SELECT EXISTS(SELECT 1 FROM LocationName WHERE id = :nameId LIMIT 1)")
//    fun isExists(nameId: String): Flow<Boolean>

    @Query("SELECT * FROM LocationName ORDER BY id DESC")
    fun getAllLocationNames(): Flow<List<LocationNameEntity>>
}