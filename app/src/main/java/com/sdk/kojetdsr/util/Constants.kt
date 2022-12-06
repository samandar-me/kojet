package com.sdk.kojetdsr.util

object Constants {
    const val BASE_URL = "https://api.openweathermap.org"
    fun getImageUrl(image: String): String {
        return "https://openweathermap.org/img/wn/$image@2x.png"
    }
    fun calculateCelsius(kelvin: Double): String {
        val celsius = (kelvin - 273.17).toInt()
        val addition = if (celsius >= 0) "+" else ""
        return "$addition$celsius"
    }
    fun calculateIntCelsius(kelvin: Double): Int {
        return (kelvin - 273.17).toInt()
    }
}