package com.sdk.kojetdsr.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sdk.kojetdsr.ui.theme.Orange

@Composable
fun MaterialDialog(
    onYesClicked: () -> Unit,
    onNoClicked: () -> Unit
) {
    Column {
        AlertDialog(onDismissRequest = {
            onNoClicked()
        },
            title = { Text(text = "Delete location?") },
            text = { Text(text = "This action cannot be undone.") },
            confirmButton = {
                Button(
                    onClick = {
                        onYesClicked()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange)
                ) {
                    Text(text = "Delete")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onNoClicked()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange)
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}