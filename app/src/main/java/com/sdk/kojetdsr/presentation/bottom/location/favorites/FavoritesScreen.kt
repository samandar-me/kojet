package com.sdk.kojetdsr.presentation.bottom.location.favorites

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sdk.kojetdsr.util.Graph

@Composable
fun FavoritesScreen(navHostController: NavHostController) {
    Button(onClick = { navHostController.navigate("${Graph.DETAILS}/Tashkent") }) {

    }
}