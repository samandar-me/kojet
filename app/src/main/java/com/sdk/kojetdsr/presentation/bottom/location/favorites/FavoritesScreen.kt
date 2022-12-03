package com.sdk.kojetdsr.presentation.bottom.location.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        items(list, key = { it.id }) {
            FavoriteNameItem(name = it.name) {

            }
        }
    }
}

@Composable
fun FavoriteNameItem(
    name: String,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable {
                onItemClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp),
            fontStyle = FontStyle.Italic
        )
    }
}

