package com.sdk.kojetdsr.presentation.map.map_screen

import android.location.Geocoder
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val geocoder: Geocoder
) : ViewModel() {
    private val _state: MutableState<MapState> = mutableStateOf(MapState())
    val state: State<MapState> get() = _state

    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.OnTextChanged -> {
                _state.value = _state.value.copy(text = event.text)
            }
            is MapEvent.OnMapClicked -> {
                _state.value = _state.value.copy(location = event.latLng)
            }
            is MapEvent.OnSearchClicked -> {
                if (event.location.isNotEmpty()) {
                    try {
                        val list = geocoder.getFromLocationName(
                            event.location, 1
                        )
                        _state.value = _state.value.copy(
                            location = LatLng(
                                list?.get(0)?.latitude!!,
                                list[0]?.longitude!!
                            )
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}