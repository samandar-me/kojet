package com.sdk.kojetdsr.presentation.bottom.location.all

import com.sdk.domain.model.LocationName

data class AllState(
    val isLoading: Boolean = false,
    val error: String = "",
    val success: List<LocationName> = emptyList()
)