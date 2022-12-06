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
        route = "${Graph.DETAILS}/{id}/{title}/{view}",
        startDestination = "DETAIL",
    ) {
        composable(
            route = "DETAIL",
            arguments = listOf(
                navArgument(
                    name = "id",
                ) {
                  type = NavType.StringType
                },
                navArgument(
                    name = "title"
                ) {
                    type = NavType.StringType
                },
                navArgument(name = "view") {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString("id") ?: ""
            val title = it.arguments?.getString("title") ?: ""
            val view = it.arguments?.getString("view") ?: ""
            DetailScreen(navHostController = navController, id = id.toInt(), title = title, view = view)
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
