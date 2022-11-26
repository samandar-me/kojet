package com.sdk.data.remote.mappers

import com.sdk.data.remote.dto.CurrentWeatherDTO
import com.sdk.data.remote.dto.WeatherDTO
import com.sdk.domain.model.CurrentWeather
import com.sdk.domain.model.Weather

internal fun CurrentWeatherDTO.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        lat = coord.lat,
        lon = coord.lon,
        weather = weatherDTO.map { it.toWeather() },
        feels_like = main.feels_like,
        humidity = main.humidity,
        pressure = main.pressure,
        temp = main.temp,
        temp_max = main.temp_max,
        temp_min = main.temp_min,
        deg = wind.deg,
        speed = wind.speed,
        name = name,
        timezone = timezone,
        country = sys.country,
        countryId = sys.id,
        sunrise = sys.sunrise,
        sunset = sys.sunset,
        type = sys.type
    )
}

fun WeatherDTO.toWeather(): Weather {
    return Weather(
        main = main,
        description = description,
        id = id,
        icon = icon
    )
}