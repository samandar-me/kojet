package com.sdk.kojetdsr.presentation.map.map_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*
import com.sdk.kojetdsr.presentation.component.SearchAppBar
import com.sdk.kojetdsr.ui.theme.Orange
import kotlinx.coroutines.launch

@Composable
fun MapScreen(navHostController: NavHostController) {
    val viewModel: MapViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }
    var searched by remember {
        mutableStateOf(false)
    }
    val state by remember {
        viewModel.state
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(state.location, 4f)
    }
    var isFabVisible by remember {
        mutableStateOf(false)
    }

    if (searched) {
        LaunchedEffect(key1 = state.location) {
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(state.location, 10f),
            )
            searched = false
        }
    }
    if (cameraPositionState.isMoving) {
        isFabVisible = false
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    cameraPositionState.animate(
                        update = CameraUpdateFactory.newLatLngZoom(state.location, 10f),
                    )
                }
            }, backgroundColor = Color.White) {
                Icon(
                    imageVector = Icons.Filled.LocationSearching,
                    contentDescription = "Location",
                    tint = Color.Black
                )
            }
        },
        bottomBar = {
            AnimatedVisibility(visible = isFabVisible) {
                Button(
                    onClick = {
                        navHostController.navigate("MAP_DETAIL")
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp, bottom = 5.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange)
                ) {
                    Text(text = "Next")
                }
            }
        },
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = MapProperties(),
            uiSettings = uiSettings,
            onMapClick = {
                viewModel.onEvent(MapEvent.OnMapClicked(it))
                isFabVisible = true
            },
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                position = state.location,
                title = state.text,
                icon = BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_BLUE
                ),
                onClick = {
                    isFabVisible = true
                    it.showInfoWindow()
                    true
                }
            )
        }
        SearchAppBar(
            text = state.text,
            onTextChange = {
                viewModel.onEvent(MapEvent.OnTextChanged(it))
            },
            onSearchClicked = {
                viewModel.onEvent(MapEvent.OnSearchClicked(it))
                searched = true
            },
            onBackClick = {
                navHostController.popBackStack()
            }
        )
    }
}