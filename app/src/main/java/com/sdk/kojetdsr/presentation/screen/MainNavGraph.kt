package com.sdk.kojetdsr.presentation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sdk.kojetdsr.presentation.bottom.detail.DetailScreen
import com.sdk.kojetdsr.presentation.bottom.location.locationscreen.LocationScreen
import com.sdk.kojetdsr.presentation.bottom.settings.SettingsScreen
import com.sdk.kojetdsr.presentation.bottom.trigger.TriggersScreen
import com.sdk.kojetdsr.presentation.map.detail.MapDetailScreen
import com.sdk.kojetdsr.presentation.map.map_screen.MapScreen
import com.sdk.kojetdsr.util.Graph

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Locations.route
    ) {
        composable(route = BottomBarScreen.Locations.route) {
            LocationScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Triggers.route) {
            TriggersScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
        mapsNavGraph(navController)
        detailsNavGraph(navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = "${Graph.DETAILS}/{title}",
        startDestination = "DETAIL",
    ) {
        composable(
            route = "DETAIL",
            arguments = listOf(
                navArgument(
                    name = "title"
                ) {
                    type = NavType.StringType
                }
            )
        ) {
            val title = it.arguments?.getString("title") ?: ""
            DetailScreen(navHostController = navController, title = title)
        }
    }
}

fun NavGraphBuilder.mapsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.MAP,
        startDestination = "MAP"
    ) {
        composable(
            route = "MAP"
        ) {
            MapScreen(navController)
        }
        composable(route = "MAP_DETAIL") {
            MapDetailScreen(navController)
        }
    }
}
