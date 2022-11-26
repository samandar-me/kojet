package com.sdk.domain.repository

import com.sdk.domain.model.CurrentWeather
import com.sdk.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeatherUseCase(query: String): Flow<Response<CurrentWeather>>
}