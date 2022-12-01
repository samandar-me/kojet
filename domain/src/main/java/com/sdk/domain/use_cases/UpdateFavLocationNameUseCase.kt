package com.sdk.domain.use_cases

import com.sdk.domain.model.FavLocationName
import com.sdk.domain.repository.WeatherRepository
import javax.inject.Inject

typealias UpdateFavLocationNameBaseUseCase = BaseUseCase<FavLocationName, Unit>

class UpdateFavLocationName @Inject constructor(
    private val repository: WeatherRepository
) : UpdateFavLocationNameBaseUseCase {
    override suspend fun invoke(params: FavLocationName) {
        repository.updateFavoriteLocationName(params.id, params.isSaved)
    }
}