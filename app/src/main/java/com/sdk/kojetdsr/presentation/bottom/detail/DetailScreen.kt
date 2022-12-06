package com.sdk.kojetdsr.presentation.bottom.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.sdk.domain.model.LocationName
import com.sdk.kojetdsr.presentation.component.ShowLottie
import com.sdk.kojetdsr.ui.theme.Orange
import com.sdk.kojetdsr.util.Time
import com.sdk.kojetdsr.R
import com.sdk.kojetdsr.presentation.component.MaterialDialog
import com.sdk.kojetdsr.presentation.component.WeatherBottomSection
import com.sdk.kojetdsr.presentation.component.WeatherItems
import com.sdk.kojetdsr.util.Constants

@Composable
fun DetailScreen(navHostController: NavHostController, id: Int, title: String, view: String) {
    val viewModel: DetailViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState().value
    val refreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading)
    var dialogState by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = navHostController) {
        viewModel.onEvent(DetailEvent.OnScreenLaunched(title))
    }
    if (state.error.isNotBlank()) {
        ShowLottie(anim = R.raw.not_found)
    }
    if (dialogState) {
        MaterialDialog(
            onYesClicked = {
                viewModel.onEvent(DetailEvent.OnDeleteClicked(LocationName(id = id, name = title)))
                dialogState = false
                navHostController.popBackStack()
            },
            onNoClicked = {
                dialogState = false
            }
        )
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        MyTopAppBar(
            title = title,
            isDeleteVisible = view.toBoolean(),
            onBackClick = { navHostController.popBackStack() },
            onDeleteClick = {
                dialogState = true
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
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                state.success?.let { weather ->
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val painter = rememberCoilPainter(
                            request = Constants.getImageUrl(
                                weather.weather?.get(0)?.icon ?: "04n"
                            )
                        )
                        Image(
                            painter = painter,
                            contentDescription = "Cloudy",
                            modifier = Modifier.size(130.dp),
                            contentScale = ContentScale.Crop,
                        )
                        Text(
                            text = "${Constants.calculateCelsius(weather.temp!!)}  °C",
                            fontSize = 50.sp,
                            modifier = Modifier.padding(end = 15.dp)
                        )
                    }
                    Text(
                        text = weather.weather?.get(0)!!.main,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    WeatherItems(weather = weather)
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp)
                    )
                    WeatherBottomSection(weather = weather)
                }
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
                    tint = MaterialTheme.colors.onSecondary
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
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
    )
}