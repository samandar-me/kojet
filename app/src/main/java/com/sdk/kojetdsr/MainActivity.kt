package com.sdk.kojetdsr

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.navigation.compose.rememberNavController
import com.sdk.kojetdsr.presentation.screen.RootNavigationGraph
import com.sdk.kojetdsr.ui.theme.KojetDsrTheme
import com.sdk.kojetdsr.ui.theme.Orange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KojetDsrTheme {
                RootNavigationGraph(navController = rememberNavController())
                BarColorsTheme(color = Orange)
            }
        }
    }
}
@Composable
fun BarColorsTheme(color: Color) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = color.toArgb()
            window.decorView.setBackgroundColor(color.toArgb())
        }
    }
}
