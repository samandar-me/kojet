package com.sdk.domain.use_cases.local

import com.sdk.domain.repository.WeatherLocalRepository
import com.sdk.domain.use_cases.base.BaseUseCase
import javax.inject.Inject

typealias SaveLanguageBaseUseCase = BaseUseCase<String, Unit>

class SaveLanguageUseCase @Inject constructor(
    private val repository: WeatherLocalRepository
) : SaveLanguageBaseUseCase {
    override suspend fun invoke(params: String) {
        repository.changeLanguage(params)
    }
}