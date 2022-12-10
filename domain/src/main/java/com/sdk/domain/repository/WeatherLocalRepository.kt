package com.sdk.domain.repository

import com.sdk.domain.model.FavoriteLocationName
import com.sdk.domain.model.LocationName
import kotlinx.coroutines.flow.Flow

interface WeatherLocalRepository {
    suspend fun saveLocationName(locationName: LocationName)
    fun getAllLocations(): Flow<List<LocationName>>
    suspend fun deleteLocationName(locationName: LocationName)
    suspend fun updateFavoriteLocationName(id: Int, isSaved: Boolean)
    suspend fun saveFavoriteName(name: FavoriteLocationName)
    fun getAllFavoriteNames(): Flow<List<FavoriteLocationName>>
    suspend fun deleteFavByName(name: String)
    suspend fun changeTheme(index: Int)
    fun getTheme(): Flow<Int>
    suspend fun changeLanguage(lan: String)
    fun getLanguage(): Flow<String>
}