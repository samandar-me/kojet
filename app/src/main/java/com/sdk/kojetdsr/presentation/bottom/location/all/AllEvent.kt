package com.sdk.kojetdsr.presentation.bottom.location.all

sealed class AllEvent {
    data class OnItemClick(val name: String): AllEvent()
    data class OnFavoriteClick(val isSaved: Boolean): AllEvent()
}