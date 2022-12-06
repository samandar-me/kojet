package com.sdk.kojetdsr.presentation.bottom.detail

import com.sdk.domain.model.CurrentWeather

data class DetailState(
    val isLoading: Boolean = false,
    val error: String = "",
    val success: CurrentWeather? = null
)