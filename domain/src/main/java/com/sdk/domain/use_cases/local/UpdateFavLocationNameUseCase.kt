package com.sdk.domain.use_cases.local

import com.sdk.domain.model.UpdateFavLocationName
import com.sdk.domain.repository.WeatherLocalRepository
import com.sdk.domain.use_cases.base.BaseUseCase
import javax.inject.Inject

typealias UpdateFavLocationNameBaseUseCase = BaseUseCase<UpdateFavLocationName, Unit>

class UpdateFavLocationName @Inject constructor(
    private val repository: WeatherLocalRepository
) : UpdateFavLocationNameBaseUseCase {
    override suspend fun invoke(params: UpdateFavLocationName) {
        repository.updateFavoriteLocationName(params.id, params.isSaved)
    }
}