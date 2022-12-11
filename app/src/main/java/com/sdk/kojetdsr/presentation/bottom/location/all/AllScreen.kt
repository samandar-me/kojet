package com.sdk.kojetdsr.presentation.bottom.location.all

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.domain.model.UpdateFavLocationName
import com.sdk.domain.model.LocationName
import com.sdk.kojetdsr.R
import com.sdk.kojetdsr.presentation.component.ShowLottie
import com.sdk.kojetdsr.ui.theme.Orange
import com.sdk.kojetdsr.util.Graph

@Composable
fun AllScreen(navHostController: NavHostController) {
    val viewModel: AllViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState().value
    if (state.isLoading) {
        ShowLottie(R.raw.loading)
    } else if (state.success.isEmpty()) {
        ShowLottie(anim = R.raw.empty)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.locations))
            },
            backgroundColor = Orange
        )
        LazyColumn(
            contentPadding = PaddingValues(5.dp)
        ) {
            itemsIndexed(state.success, key = { _, item -> item.id }) { index, item ->
                LocationNameItem(
                    locationName = item,
                    onFavoriteClick = {
                        viewModel.onEvent(
                            AllEvent.OnFavoriteClick(
                                UpdateFavLocationName(
                                    item.id,
                                    item.name,
                                    !item.isSaved
                                )
                            )
                        )
                    },
                    onItemClick = {
                        navHostController.navigate("${Graph.DETAILS}/${it.id}/${it.name}/true")
                    }
                )
                if (index < state.success.lastIndex) {
                    Divider(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
//"${Graph.DETAILS}/Tashkent"

@Composable
fun LocationNameItem(
    locationName: LocationName,
    onItemClick: (LocationName) -> Unit,
    onFavoriteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable {
                onItemClick(locationName)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = locationName.name,
            fontSize = 16.sp,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier
                .weight(1f)
                .padding(start = 5.dp)
        )
        Spacer(modifier = Modifier.width(2.dp))
        IconToggleButton(checked = locationName.isSaved, onCheckedChange = {
            onFavoriteClick()
        }) {
            Icon(
                imageVector = if (locationName.isSaved) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite",
                tint = MaterialTheme.colors.onSecondary
            )
        }
    }
}