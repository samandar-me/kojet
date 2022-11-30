package com.sdk.domain.use_cases

import com.sdk.domain.model.LocationName
import com.sdk.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetLocationNamesBaseUseCase = BaseUseCase<String, Flow<List<LocationName>>>

class GetLocationNamesUseCase @Inject constructor(
    private val repository: WeatherRepository
) : GetLocationNamesBaseUseCase {
    override suspend fun invoke(params: String): Flow<List<LocationName>> =
        repository.getAllLocations()
}