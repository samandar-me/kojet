package com.sdk.kojetdsr.presentation.map

import com.google.android.gms.maps.model.LatLng

data class MapState(
    val text: String = "",
    val latLng: LatLng = LatLng(51.6683, 39.1919),
    val searchedLocation: LatLng = LatLng(0.0, 0.0)
)