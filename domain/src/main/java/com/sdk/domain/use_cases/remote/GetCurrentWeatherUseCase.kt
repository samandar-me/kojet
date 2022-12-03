package com.sdk.domain.use_cases.remote

import com.sdk.domain.model.CurrentWeather
import com.sdk.domain.repository.WeatherRemoteRepository
import com.sdk.domain.use_cases.base.BaseUseCase
import com.sdk.domain.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetCurrentWeatherBaseUseCase = BaseUseCase<String, Flow<Response<CurrentWeather>>>

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRemoteRepository
) : GetCurrentWeatherBaseUseCase {
    override suspend fun invoke(params: String): Flow<Response<CurrentWeather>> {
        return repository.getCurrentWeatherUseCase(params)
    }
}