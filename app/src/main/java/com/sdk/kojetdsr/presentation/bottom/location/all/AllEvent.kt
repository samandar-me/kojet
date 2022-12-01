package com.sdk.kojetdsr.presentation.bottom.location.all

import com.sdk.domain.model.FavLocationName

sealed class AllEvent {
    data class OnItemClick(val name: String): AllEvent()
    data class OnFavoriteClick(val name: FavLocationName): AllEvent()
}