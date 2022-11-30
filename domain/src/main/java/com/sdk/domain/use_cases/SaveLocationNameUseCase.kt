package com.sdk.domain.use_cases

import com.sdk.domain.model.LocationName
import com.sdk.domain.repository.WeatherRepository
import javax.inject.Inject

typealias SaveLocationNameBaseUseCase = BaseUseCase<String, Unit>

class SaveLocationNameUseCase @Inject constructor(
    private val repository: WeatherRepository
): SaveLocationNameBaseUseCase {
    override suspend fun invoke(params: String) {
        repository.saveLocationName(LocationName(name = params))
    }
}