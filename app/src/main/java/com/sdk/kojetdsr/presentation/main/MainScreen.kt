package com.sdk.kojetdsr.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sdk.kojetdsr.presentation.component.BottomBar
import com.sdk.kojetdsr.presentation.screen.BottomBarScreen
import com.sdk.kojetdsr.presentation.screen.HomeNavGraph
import com.sdk.kojetdsr.ui.theme.Grey10
import com.sdk.kojetdsr.ui.theme.Orange
import com.sdk.kojetdsr.util.Graph

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    var isFab by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = navController) {
        navController.currentBackStackEntryFlow.collect {
            isFab = when (it.destination.route) {
                BottomBarScreen.Locations.route -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(navController = navController)
        },
        floatingActionButton = {
            if (isFab) {
                FloatingActionButton(
                    onClick = { navController.navigate(Graph.MAP) },
                    backgroundColor = Orange
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add", tint = Grey10)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(bottom = it.calculateBottomPadding())
        ) {
            HomeNavGraph(navController = navController)
        }
    }
}