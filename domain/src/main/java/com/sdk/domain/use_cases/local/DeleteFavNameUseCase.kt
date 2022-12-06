package com.sdk.domain.use_cases.local

import com.sdk.domain.repository.WeatherLocalRepository
import com.sdk.domain.use_cases.base.BaseUseCase
import javax.inject.Inject

typealias DeleteFavNameBaseUseCase = BaseUseCase<String, Unit>

class DeleteFavNameUseCase @Inject constructor(
    private val repository: WeatherLocalRepository
): DeleteFavNameBaseUseCase {
    override suspend fun invoke(params: String) {
        repository.deleteFavByName(params)
    }
}