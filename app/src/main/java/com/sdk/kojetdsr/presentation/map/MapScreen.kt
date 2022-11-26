package com.sdk.kojetdsr.presentation.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.sdk.kojetdsr.presentation.component.SearchAppBar

@Composable
fun MapScreen(navHostController: NavHostController) {
    val scaffoldState = rememberScaffoldState()

    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
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

            }
        )
        SearchAppBar(
            text = "",
            onTextChange = {

            },
            onSearchClicked = {

            },
            onBackClick = {
                navHostController.popBackStack()
            }
        )
    }
}