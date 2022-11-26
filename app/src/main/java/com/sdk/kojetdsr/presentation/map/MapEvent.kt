package com.sdk.kojetdsr.presentation.map

import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {
    data class OnTextChanged(val text: String): MapEvent()
    data class OnMapClicked(val latLng: LatLng): MapEvent()
    data class OnSearchClicked(val location: String): MapEvent()
}