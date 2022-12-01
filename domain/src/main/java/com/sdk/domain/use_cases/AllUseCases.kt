package com.sdk.domain.use_cases

data class AllUseCases(
    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    val saveLocationNameBaseUseCase: SaveLocationNameBaseUseCase,
    val getLocationNamesUseCase: GetLocationNamesUseCase,
    val updateLocationNameUseCase: UpdateLocationNameUseCase,
    val updateFavLocationName: UpdateFavLocationName
)