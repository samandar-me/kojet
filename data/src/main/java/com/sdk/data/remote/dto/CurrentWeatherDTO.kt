package com.sdk.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDTO(
    val base: String?,
    val clouds: Clouds?,
    val cod: Int?,
    val coord: Coord?,
    val dt: Int?,
    val id: Int?,
    val main: Main?,
    val name: String?,
    val sys: Sys?,
    val timezone: Int?,
    val visibility: Int?,
    @SerializedName("weather")
    val weatherDTO: List<WeatherDTO>?,
    val wind: Wind?
)