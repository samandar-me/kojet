package com.sdk.kojetdsr.presentation.bottom.location.all

import com.sdk.domain.model.UpdateFavLocationName

sealed class AllEvent {
    data class OnFavoriteClick(val name: UpdateFavLocationName): AllEvent()
}