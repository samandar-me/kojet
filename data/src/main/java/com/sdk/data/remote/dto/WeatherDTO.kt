package com.sdk.data.remote.dto

data class WeatherDTO(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)