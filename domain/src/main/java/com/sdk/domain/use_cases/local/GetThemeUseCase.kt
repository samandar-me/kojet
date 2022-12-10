package com.sdk.domain.use_cases.local

import com.sdk.domain.repository.WeatherLocalRepository
import com.sdk.domain.use_cases.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetThemeBaseUseCase = BaseUseCase<String, Flow<Int>>

class GetThemeUseCase @Inject constructor(
    private val repository: WeatherLocalRepository
): GetThemeBaseUseCase {
    override suspend fun invoke(params: String): Flow<Int> {
        return repository.getTheme()
    }
}