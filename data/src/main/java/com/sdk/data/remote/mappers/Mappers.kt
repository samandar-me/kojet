package com.sdk.data.remote.mappers

import com.sdk.data.local.entity.LocationNameEntity
import com.sdk.data.remote.dto.CurrentWeatherDTO
import com.sdk.data.remote.dto.WeatherDTO
import com.sdk.domain.model.CurrentWeather
import com.sdk.domain.model.LocationName
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

internal fun WeatherDTO.toWeather(): Weather {
    return Weather(
        main = main,
        description = description,
        id = id,
        icon = icon
    )
}

fun LocationName.toLocationEntity(): LocationNameEntity {
    return LocationNameEntity(
        id = id,
        name = name,
        isSaved = isSaved
    )
}

fun LocationNameEntity.toLocationName(): LocationName {
    return LocationName(
        id = id,
        name = name,
        isSaved = isSaved
    )
}

