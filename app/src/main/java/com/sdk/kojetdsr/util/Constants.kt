package com.sdk.kojetdsr.util

object Constants {
    const val BASE_URL = "https://api.openweathermap.org"
    fun getImageUrl(image: String): String {
        return "https://openweathermap.org/img/wn/$image@2x.png"
    }
}