package com.sdk.domain.use_cases

import com.sdk.domain.model.LocationName
import com.sdk.domain.repository.WeatherRepository
import javax.inject.Inject

typealias UpdateLocationNameBaseUseCase = BaseUseCase<LocationName, Unit>

class UpdateLocationNameUseCase @Inject constructor(
    private val repository: WeatherRepository
): UpdateLocationNameBaseUseCase {
    override suspend fun invoke(params: LocationName) {
        repository.updateLocationName(params)
    }
}