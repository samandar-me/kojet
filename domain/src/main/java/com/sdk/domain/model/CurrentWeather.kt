package com.sdk.domain.model

data class CurrentWeather(
    val lat: Double?,
    val lon: Double?,
    val weather: List<Weather>?,
    val feels_like: Double?,
    val humidity: Int?,
    val pressure: Int?,
    val temp: Double?,
    val temp_max: Double?,
    val temp_min: Double?,
    val deg: Int?,
    val speed: Double?,
    val name: String?,
    val timezone: Int?,
    val country: String?,
    val countryId: Int?,
    val sunrise: Int?,
    val sunset: Int?,
    val type: Int?
)