package com.sdk.domain.use_cases.local

import com.sdk.domain.model.FavoriteLocationName
import com.sdk.domain.repository.WeatherLocalRepository
import com.sdk.domain.use_cases.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetFavoriteNamesBaseUseCase = BaseUseCase<String, Flow<List<FavoriteLocationName>>>

class GetFavoriteNamesUseCase @Inject constructor(
    private val repository: WeatherLocalRepository
) : GetFavoriteNamesBaseUseCase {
    override suspend fun invoke(params:String): Flow<List<FavoriteLocationName>> {
        return repository.getAllFavoriteNames()
    }
}