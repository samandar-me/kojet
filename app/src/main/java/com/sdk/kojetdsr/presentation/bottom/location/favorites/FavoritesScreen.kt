package com.sdk.kojetdsr.presentation.bottom.location.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.domain.model.LocationName
import com.sdk.kojetdsr.util.Graph

@Composable
fun FavoritesScreen(navHostController: NavHostController) {
    val viewModel: FavoriteViewModel = hiltViewModel()
    val list = viewModel.state.collectAsState().value

    LazyColumn(
        contentPadding = PaddingValues(5.dp)
    ) {
        itemsIndexed(list, key = { _, item -> item.id }) { index, item ->
            FavoriteNameItem(name = item.name, index = index) {
                navHostController.navigate("${Graph.DETAILS}/$it/false")
            }
        }
    }
}

@Composable
fun FavoriteNameItem(
    name: String,
    index: Int,
    onItemClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable {
                onItemClick(name)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${index + 1}.",
            fontStyle = FontStyle.Italic,
            color = Color.Black,
            fontSize = 15.sp
        )
        Text(
            text = name,
            maxLines = 1,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 35.dp),
            fontStyle = FontStyle.Italic,
        )
    }
}

