package com.sdk.kojetdsr.presentation.map

import android.app.Application
import android.location.Address
import android.location.Geocoder
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.maps.model.LatLng
import com.sdk.domain.use_cases.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val app: Application,
    private val useCases: AllUseCases
) : AndroidViewModel(app) {
    private val _state: MutableState<MapState> = mutableStateOf(MapState())
    val state: State<MapState> get() = _state

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.OnTextChanged -> {
                _state.value = _state.value.copy(text = event.text)
            }
            is MapEvent.OnMapClicked -> {
                _state.value = _state.value.copy(latLng = event.latLng)
            }
            is MapEvent.OnSearchClicked -> {
                if (event.location.isNotEmpty()) {
                    val geocoder = Geocoder(app.applicationContext)
                    try {
                        geocoder.getFromLocationName(
                            event.location, 1
                        ) {
                            _state.value = _state.value.copy(
                                searchedLocation = LatLng(
                                    it[0].latitude,
                                    it[0].longitude
                                )
                            )
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}