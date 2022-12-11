package com.sdk.kojetdsr.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sdk.kojetdsr.R
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
            title = { Text(text = stringResource(R.string.delete_location)) },
            text = { Text(text = stringResource(R.string.this_ac_cannot)) },
            confirmButton = {
                Button(
                    onClick = {
                        onYesClicked()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange)
                ) {
                    Text(text = stringResource(R.string.delete))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onNoClicked()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange)
                ) {
                    Text(text = stringResource(R.string.cancel))
                }
            }
        )
    }
}