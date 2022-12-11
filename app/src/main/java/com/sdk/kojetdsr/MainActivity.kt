package com.sdk.kojetdsr

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.sdk.data.local.manager.DataStoreManager
import com.sdk.data.local.manager.LocalizationManager
import com.sdk.kojetdsr.presentation.bottom.settings.SettingsViewModel
import com.sdk.kojetdsr.presentation.screen.RootNavigationGraph
import com.sdk.kojetdsr.ui.theme.KojetDsrTheme
import com.sdk.kojetdsr.ui.theme.Orange
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: SettingsViewModel = hiltViewModel()
            val language = viewModel.language.collectAsState().value
            val darkTheme = when(viewModel.theme.collectAsState().value) {
                1 -> false
                2 -> true
                else -> isSystemInDarkTheme()
            }
            KojetDsrTheme(darkTheme = darkTheme) {
                RootNavigationGraph(navController = rememberNavController())
                BarColorsTheme(color = Orange)
            }
            LocalizationManager.setLanguage(this, language)
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

