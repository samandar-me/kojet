package com.sdk.data.repository

import com.sdk.data.local.database.FavoriteDao
import com.sdk.data.local.database.LocationNameDao
import com.sdk.data.remote.api.WeatherService
import com.sdk.data.remote.mappers.*
import com.sdk.domain.model.CurrentWeather
import com.sdk.domain.model.FavoriteLocationName
import com.sdk.domain.model.LocationName
import com.sdk.domain.repository.WeatherLocalRepository
import com.sdk.domain.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherLocalRepositoryImpl @Inject constructor(
    private val dao: LocationNameDao,
    private val favoriteDao: FavoriteDao
) : WeatherLocalRepository {

    override suspend fun saveLocationName(locationName: LocationName) {
        dao.saveLocationName(locationName.toLocationEntity())
    }

    override fun getAllLocations(): Flow<List<LocationName>> = flow {
        dao.getAllLocationNames().collect { name ->
            emit(name.map { it.toLocationName() })
        }
    }

    override suspend fun deleteLocationName(locationName: LocationName) {
        dao.deleteLocationName(locationName.toLocationEntity())
    }

    override suspend fun updateFavoriteLocationName(id: Int, isSaved: Boolean) {
        dao.updateFavLocation(id, isSaved)
    }

    override suspend fun saveFavoriteName(name: FavoriteLocationName) {
        favoriteDao.saveFavoriteName(name.toFavoriteEntity())
    }

    override fun getAllFavoriteNames(): Flow<List<FavoriteLocationName>> = flow {
        favoriteDao.getAllFavoriteLocations().collect { name ->
            emit(name.map { it.toFavoriteName() })
        }
    }

    override suspend fun deleteFavoriteName(name: FavoriteLocationName) {
        favoriteDao.deleteFavoriteName(name.toFavoriteEntity())
    }
}