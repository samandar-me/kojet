package com.sdk.domain.use_cases

import com.sdk.domain.model.CurrentWeather
import com.sdk.domain.repository.WeatherRepository
import com.sdk.domain.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetCurrentWeatherBaseUseCase = BaseUseCase<String, Flow<Response<CurrentWeather>>>

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) : GetCurrentWeatherBaseUseCase {
    override suspend fun invoke(params: String): Flow<Response<CurrentWeather>> {
        return repository.getCurrentWeatherUseCase(params)
    }
}