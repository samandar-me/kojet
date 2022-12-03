package com.sdk.domain.use_cases.local

import com.sdk.domain.model.FavoriteLocationName
import com.sdk.domain.repository.WeatherLocalRepository
import com.sdk.domain.use_cases.base.BaseUseCase
import javax.inject.Inject

typealias SaveFavoriteNameBaseUseCase = BaseUseCase<FavoriteLocationName, Unit>

class SaveFavoriteNameUseCase @Inject constructor(
    private val repository: WeatherLocalRepository
): SaveFavoriteNameBaseUseCase {
    override suspend fun invoke(params: FavoriteLocationName) {
        repository.saveFavoriteName(params)
    }
}