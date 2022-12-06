package com.sdk.domain.use_cases.local

import com.sdk.domain.model.LocationName
import com.sdk.domain.repository.WeatherLocalRepository
import com.sdk.domain.use_cases.base.BaseUseCase
import javax.inject.Inject

typealias DeleteLocationNameBaseUseCase = BaseUseCase<LocationName,Unit>

class DeleteLocationNameUseCase @Inject constructor(
    private val repository: WeatherLocalRepository
) : DeleteLocationNameBaseUseCase {
    override suspend fun invoke(params: LocationName) {
        repository.deleteLocationName(params)
    }
}