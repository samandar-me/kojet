package com.sdk.kojetdsr.presentation.screen

import com.sdk.kojetdsr.R

sealed class BottomBarScreen(
    val route: String,
    val icon: Int
) {
    object Locations : BottomBarScreen(
        route = "Locations",
        icon = R.drawable.ic_baseline_location_on_24
    )

    object Triggers : BottomBarScreen(
        route = "Triggers",
        icon = R.drawable.ic_baseline_notifications_24
    )

    object Settings : BottomBarScreen(
        route = "Settings",
        icon = R.drawable.ic_baseline_settings_24
    )
}