package com.sdk.kojetdsr.presentation.bottom.detail

import com.sdk.domain.model.LocationName

sealed class DetailEvent {
    data class OnDeleteClicked(val name: LocationName): DetailEvent()
    data class OnScreenLaunched(val name: String): DetailEvent()
    object OnRefreshSwiped: DetailEvent()
}