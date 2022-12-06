package com.sdk.kojetdsr.presentation.bottom.detail

sealed class DetailEvent {
    data class OnDeleteClicked(val name: String): DetailEvent()
    data class OnScreenLaunched(val name: String): DetailEvent()
    object OnRefreshSwiped: DetailEvent()
}