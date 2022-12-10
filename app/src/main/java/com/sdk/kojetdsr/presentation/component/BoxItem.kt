package com.sdk.kojetdsr.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sdk.kojetdsr.ui.theme.Orange

@Composable
fun BoxItem(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(200.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = 5.dp,
        backgroundColor = Orange
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
        ) {
            content(this)
        }
    }
}