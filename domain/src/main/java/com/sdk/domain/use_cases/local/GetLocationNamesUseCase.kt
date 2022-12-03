package com.sdk.domain.use_cases.local

import com.sdk.domain.model.LocationName
import com.sdk.domain.repository.WeatherLocalRepository
import com.sdk.domain.use_cases.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetLocationNamesBaseUseCase = BaseUseCase<String, Flow<List<LocationName>>>

class GetLocationNamesUseCase @Inject constructor(
    private val repository: WeatherLocalRepository
) : GetLocationNamesBaseUseCase {
    override suspend fun invoke(params: String): Flow<List<LocationName>> =
        repository.getAllLocations()
}