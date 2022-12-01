package com.sdk.kojetdsr.presentation.bottom.location.all

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.domain.model.LocationName
import com.sdk.kojetdsr.presentation.component.Empty
import com.sdk.kojetdsr.presentation.component.Loading

@Composable
fun AllScreen(navHostController: NavHostController) {
    val viewModel: AllViewModel = hiltViewModel()
    val state by remember {
        viewModel.state
    }
    if (state.isLoading) {
        Loading()
    }
    state.success?.let { list ->
        if (list.isEmpty()) {
            Empty()
        }
        LazyColumn(
            contentPadding = PaddingValues(5.dp)
        ) {
            itemsIndexed(list) { index, item ->
                LocationNameItem(
                    locationName = item,
                    onFavoriteClick = {

                    },
                    onItemClick = {

                    }
                )
                if (index < list.lastIndex) {
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
    onItemClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val icon by remember {
        mutableStateOf(if (locationName.isSaved) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = locationName.name,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f).padding(start = 5.dp)
        )
        Spacer(modifier = Modifier.width(2.dp))
        IconButton(onClick = { onFavoriteClick() }) {
            Icon(imageVector = icon, contentDescription = "Favorite", tint = Color.Black)
        }
    }
}