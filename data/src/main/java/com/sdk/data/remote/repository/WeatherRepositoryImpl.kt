package com.sdk.data.remote.repository

import com.sdk.data.remote.api.WeatherService
import com.sdk.data.remote.mappers.toCurrentWeather
import com.sdk.domain.model.CurrentWeather
import com.sdk.domain.repository.WeatherRepository
import com.sdk.domain.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val service: WeatherService
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
}