package com.sdk.domain.use_cases.base

import com.sdk.domain.use_cases.local.*
import com.sdk.domain.use_cases.remote.*

data class AllUseCases(
    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    val saveLocationNameBaseUseCase: SaveLocationNameBaseUseCase,
    val getLocationNamesUseCase: GetLocationNamesUseCase,
    val updateFavLocationName: UpdateFavLocationName,
    val saveFavoriteNameUseCase: SaveFavoriteNameUseCase,
    val getFavoriteNamesUseCase: GetFavoriteNamesUseCase,
    val deleteFavNameUseCase: DeleteFavNameUseCase,
    val deleteLocationNameUseCase: DeleteLocationNameUseCase
)