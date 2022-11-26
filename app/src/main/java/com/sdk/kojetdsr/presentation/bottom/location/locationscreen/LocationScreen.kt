package com.sdk.kojetdsr.presentation.bottom.location.locationscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import com.sdk.kojetdsr.presentation.bottom.location.all.AllScreen
import com.sdk.kojetdsr.presentation.bottom.location.favorites.FavoritesScreen
import com.sdk.kojetdsr.presentation.screen.BottomBarScreen
import com.sdk.kojetdsr.ui.theme.Grey10
import com.sdk.kojetdsr.ui.theme.Grey95
import com.sdk.kojetdsr.ui.theme.Orange
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LocationScreen(navController: NavHostController) {
    val pagerState = rememberPagerState(pageCount = 2)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(text = "Locations")
            },
            backgroundColor = Orange
        )
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState, navController = navController)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState: PagerState) {
    val text = listOf("All", "Favorites")
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Orange,
        contentColor = Color.White,
        divider = {
            TabRowDefaults.Divider(
                thickness = 3.dp,
                color = Grey10
            )
        },
        indicator = { pos ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, pos),
                height = 3.dp,
                color = Color.White
            )
        }
    ) {
        text.forEachIndexed { index, s ->
            Tab(
                text = {
                    Text(
                        text = s,
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(pagerState: PagerState, navController: NavHostController) {
    HorizontalPager(state = pagerState) {
        when(it) {
            0 -> AllScreen(navController)
            1 -> FavoritesScreen(navController)
        }
    }
}