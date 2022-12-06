package com.sdk.kojetdsr.presentation.bottom.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sdk.kojetdsr.presentation.component.ShowLottie
import com.sdk.kojetdsr.ui.theme.Orange
import com.sdk.kojetdsr.util.Time
import com.sdk.kojetdsr.R
import com.sdk.kojetdsr.util.Constants

@Composable
fun DetailScreen(navHostController: NavHostController, title: String, view: String) {
    val viewModel: DetailViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState().value
    val refreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading)
    LaunchedEffect(key1 = navHostController) {
        viewModel.onEvent(DetailEvent.OnScreenLaunched(title))
    }
    if (state.error.isNotBlank()) {
        ShowLottie(anim = R.raw.not_found)
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        MyTopAppBar(
            title = title,
            isDeleteVisible = view.toBoolean(),
            onBackClick = { navHostController.popBackStack() },
            onDeleteClick = {

            }
        )

        SwipeRefresh(
            state = refreshState,
            onRefresh = { viewModel.onEvent(DetailEvent.OnRefreshSwiped) },
            indicator = { state, tr ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = tr,
                    contentColor = Orange
                )
            },
            modifier = Modifier.padding(2.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = Time.getCurrentTime(),
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                state.success?.let { weather ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        val painter = rememberCoilPainter(request = Constants.getImageUrl(weather.weather?.get(0)?.icon ?: "04n"))
                        Image(painter = painter, contentDescription = "Cloudy", modifier = Modifier.size(100.dp))
                        println("@@$weather")
                    }
                }
                println("@@${state.success}")
            }
        }
    }
}

@Composable
fun MyTopAppBar(
    title: String,
    isDeleteVisible: Boolean,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        backgroundColor = Orange,
        navigationIcon = {
            IconButton(
                onClick = { onBackClick() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back",
                    tint = Color.Black
                )
            }
        },
        actions = {
            if (isDeleteVisible) {
                IconButton(
                    onClick = { onDeleteClick() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete",
                        tint = Color.Black
                    )
                }
            }
        }
    )
}