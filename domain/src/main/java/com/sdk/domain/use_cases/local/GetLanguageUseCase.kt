package com.sdk.domain.use_cases.local

import com.sdk.domain.repository.WeatherLocalRepository
import com.sdk.domain.use_cases.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetLanguageBaseUseCase = BaseUseCase<String, Flow<String>>

class GetLanguageUseCase @Inject constructor(
    private val repository: WeatherLocalRepository
): GetLanguageBaseUseCase {
    override suspend fun invoke(params: String): Flow<String> {
        return repository.getLanguage()
    }
}