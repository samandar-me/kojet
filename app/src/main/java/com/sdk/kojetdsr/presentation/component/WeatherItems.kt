package com.sdk.kojetdsr.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.sdk.domain.model.CurrentWeather
import com.sdk.kojetdsr.R
import com.sdk.kojetdsr.util.Constants

@Composable
fun WeatherItems(
    weather: CurrentWeather,
    modifier: Modifier = Modifier
) {
    val list = listOf(
        stringResource(id = R.string.morning),
        stringResource(id = R.string.day),
        stringResource(id = R.string.evening),
        stringResource(id = R.string.night)
    )
    val minus = listOf(2,0,4,7)
    val temp by remember {
        mutableStateOf(Constants.calculateIntCelsius(weather.temp!!))
    }
    val icon by remember {
        mutableStateOf(weather.weather?.get(0)!!.icon)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Item(
            title = list[0],
            icon = icon,
            celsius = temp,
            minus = minus[0]
        )
        Item(
            title = list[1],
            icon = icon,
            celsius = temp,
            minus = minus[1]
        )
        Item(
            title = list[2],
            icon = icon,
            celsius = temp,
            minus = minus[2]
        )
        Item(
            title = list[3],
            icon = icon,
            celsius = temp,
            minus = minus[3]
        )
    }
}

@Composable
fun Item(
    title: String,
    icon: String,
    celsius: Int,
    minus: Int
) {
    val temp by remember {
        mutableStateOf(celsius - minus)
    }
    val add by remember {
        mutableStateOf(if (temp > 0.0) "+" else "")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center
        )
        val painter = rememberCoilPainter(request = Constants.getImageUrl(icon))
        Image(painter = painter, contentDescription = "painter",Modifier.size(60.dp))
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = "$add $temp °C"
        )
    }
}