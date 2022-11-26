package com.sdk.kojetdsr.presentation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sdk.kojetdsr.presentation.main.MainScreen
import com.sdk.kojetdsr.util.Graph

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ) {
        composable(route = Graph.HOME) {
            MainScreen()
        }
    }
}