package com.sdk.kojetdsr.presentation.bottom.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sdk.kojetdsr.ui.theme.Orange

@Composable
fun SettingsScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(text = "Settings")
            },
            backgroundColor = Orange
        )
    }
}