package com.sdk.kojetdsr.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sdk.domain.model.CurrentWeather
import com.sdk.kojetdsr.R

@Composable
fun WeatherBottomSection(
    weather: CurrentWeather,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Item(
            icon = R.drawable.wind,
            text = "wind ${weather.speed} m/s"
        )
        Item(
            icon = R.drawable.hum,
            text = "humidity ${weather.humidity}%"
        )
        Item(
            icon = R.drawable.tire,
            text = "pressure ${weather.pressure} hpa"
        )
    }
}

@Composable
fun Item(
    @DrawableRes icon: Int,
    text: String
) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(id = icon), contentDescription = "", modifier = Modifier.size(38.dp))
        Text(text = text, modifier = Modifier.padding(start = 15.dp))
    }
}