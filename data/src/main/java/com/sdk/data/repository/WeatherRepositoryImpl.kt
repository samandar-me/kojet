package com.sdk.data.repository

import com.sdk.data.local.database.LocationNameDao
import com.sdk.data.remote.api.WeatherService
import com.sdk.data.remote.mappers.toCurrentWeather
import com.sdk.data.remote.mappers.toLocationEntity
import com.sdk.data.remote.mappers.toLocationName
import com.sdk.domain.model.CurrentWeather
import com.sdk.domain.model.LocationName
import com.sdk.domain.repository.WeatherRepository
import com.sdk.domain.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val service: WeatherService,
    private val dao: LocationNameDao
) : WeatherRepository {
    override suspend fun getCurrentWeatherUseCase(query: String): Flow<Response<CurrentWeather>> {
        return flow {
            emit(Response.Loading)
            try {
                val response = service.getCurrentWeather(query)
                if (response.isSuccessful) {
                    val data = response.body()?.toCurrentWeather()!!
                    emit(Response.Success(data))
                }
            } catch (e: Exception) {
                emit(Response.Error(e.stackTraceToString()))
                println(e.message)
            }
        }
    }

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
}