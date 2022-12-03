package com.sdk.domain.use_cases.local

import com.sdk.domain.model.FavoriteLocationName
import com.sdk.domain.repository.WeatherLocalRepository
import com.sdk.domain.use_cases.base.BaseUseCase
import javax.inject.Inject

typealias DeleteFavoriteNameBaseUseCase = BaseUseCase<FavoriteLocationName, Unit>

class DeleteFavoriteNameUseCase @Inject constructor(
    private val repository: WeatherLocalRepository
): DeleteFavoriteNameBaseUseCase {
    override suspend fun invoke(params: FavoriteLocationName) {
        repository.deleteFavoriteName(params)
    }
}