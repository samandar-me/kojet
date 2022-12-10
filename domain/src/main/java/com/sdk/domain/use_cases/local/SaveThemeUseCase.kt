package com.sdk.domain.use_cases.local

import com.sdk.domain.repository.WeatherLocalRepository
import com.sdk.domain.use_cases.base.BaseUseCase
import javax.inject.Inject

typealias SaveThemeBaseUseCase = BaseUseCase<Int,Unit>

class SaveThemeUseCase @Inject constructor(
    private val repository: WeatherLocalRepository
): SaveThemeBaseUseCase {
    override suspend fun invoke(params: Int) {
        repository.changeTheme(params)
    }
}