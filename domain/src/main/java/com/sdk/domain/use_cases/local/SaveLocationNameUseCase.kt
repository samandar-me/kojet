package com.sdk.domain.use_cases.local

import com.sdk.domain.model.LocationName
import com.sdk.domain.repository.WeatherLocalRepository
import com.sdk.domain.use_cases.base.BaseUseCase
import javax.inject.Inject

typealias SaveLocationNameBaseUseCase = BaseUseCase<String, Unit>

class SaveLocationNameUseCase @Inject constructor(
    private val repository: WeatherLocalRepository
): SaveLocationNameBaseUseCase {
    override suspend fun invoke(params: String) {
        repository.saveLocationName(LocationName(name = params))
    }
}