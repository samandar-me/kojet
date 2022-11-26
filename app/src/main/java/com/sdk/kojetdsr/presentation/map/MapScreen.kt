package com.sdk.kojetdsr.presentation.map

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*
import com.sdk.kojetdsr.presentation.component.SearchAppBar

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MapScreen(navHostController: NavHostController) {
    val viewModel: MapViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()

    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }

    val state by remember {
        viewModel.state
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }, backgroundColor = Color.White) {
                Icon(
                    imageVector = Icons.Filled.LocationSearching,
                    contentDescription = "Location",
                    tint = Color.Black
                )
            }
        }
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = MapProperties(),
            uiSettings = uiSettings,
            onMapClick = {
                viewModel.onEvent(MapEvent.OnMapClicked(it))
            },
            cameraPositionState = CameraPositionState(
                CameraPosition(
                    state.searchedLocation,
                    4f,
                    5f,
                    0f
                )
            ),
        ) {
            Marker(
                position = state.latLng,
                title = state.text,
                icon = BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_BLUE
                )
            ) {
                it.showInfoWindow()
            }
        }
        SearchAppBar(
            text = state.text,
            onTextChange = {
                viewModel.onEvent(MapEvent.OnTextChanged(it))
            },
            onSearchClicked = {
                viewModel.onEvent(MapEvent.OnSearchClicked(it))
            },
            onBackClick = {
                navHostController.popBackStack()
            }
        )
    }
}