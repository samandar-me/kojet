package com.sdk.kojetdsr.presentation.bottom.location.locationscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.sdk.kojetdsr.presentation.bottom.location.all.AllScreen
import com.sdk.kojetdsr.presentation.bottom.location.favorites.FavoritesScreen
import com.sdk.kojetdsr.ui.theme.Orange

@Composable
fun LocationScreen(navController: NavHostController) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("All", "Favorites")
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(text = "Locations")
            },
            backgroundColor = Orange
        )

        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = Orange
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = {
                        Text(
                            text = title,
                            color = if (tabIndex == index) Color.White else Color.LightGray
                        )
                    }
                )
            }
        }
        when (tabIndex) {
            0 -> AllScreen(navHostController = navController)
            1 -> FavoritesScreen(navHostController = navController)
        }
    }
}
