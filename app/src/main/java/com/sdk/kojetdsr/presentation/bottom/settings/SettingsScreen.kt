package com.sdk.kojetdsr.presentation.bottom.settings

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sdk.kojetdsr.BuildConfig
import com.sdk.kojetdsr.MainActivity
import com.sdk.kojetdsr.R
import com.sdk.kojetdsr.presentation.component.BoxItem
import com.sdk.kojetdsr.ui.theme.Orange

@Composable
fun SettingsScreen() {
    val viewModel: SettingsViewModel = hiltViewModel()
    val language = viewModel.language.collectAsState().value
    val theme = viewModel.theme.collectAsState().value
    val activity = (LocalContext.current as MainActivity)
    Column(
        modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.settings))
            },
            backgroundColor = Orange
        )
        BoxItem(
            modifier = Modifier.padding(PaddingValues(4.dp))
        ) {
            TopTextIcon(text = R.string.language, icon = R.drawable.ic_baseline_language_24)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CardItem(icon = R.drawable.uk, isBackWhite = language == "en") {
                    viewModel.onEvent(SettingsEvent.ChangeLanguage("en"))
                    reCreate(activity)
                }
                CardItem(icon = R.drawable.rus, isBackWhite = language == "ru") {
                    viewModel.onEvent(SettingsEvent.ChangeLanguage("ru"))
                    reCreate(activity)
                }
                CardItem(icon = R.drawable.uzb, isBackWhite = language == "uz") {
                    viewModel.onEvent(SettingsEvent.ChangeLanguage("uz"))
                    reCreate(activity)
                }
            }
        }
        BoxItem(
            modifier = Modifier.padding(PaddingValues(4.dp))
        ) {
            TopTextIcon(text = R.string.theme, icon = R.drawable.ic_baseline_color_lens_24)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CardItem(icon = R.drawable.ic_baseline_auto_awesome_24, isBackWhite = theme == 0) {
                    viewModel.onEvent(SettingsEvent.ChangeTheme(0))
                }
                CardItem(icon = R.drawable.day, isBackWhite = theme == 1) {
                    viewModel.onEvent(SettingsEvent.ChangeTheme(1))
                }
                CardItem(icon = R.drawable.night, isBackWhite = theme == 2) {
                    viewModel.onEvent(SettingsEvent.ChangeTheme(2))
                }
            }
        }
        Text(
            text = "${BuildConfig.VERSION_NAME}.V",
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
        )
    }
}

@Composable
fun TopTextIcon(
    @StringRes text: Int,
    @DrawableRes icon: Int
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(5.dp)
    ) {
        Icon(painter = painterResource(id = icon),
            contentDescription = "Text"
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = stringResource(id = text),
            fontSize = 17.sp
        )
    }
}

@Composable
fun CardItem(
    @DrawableRes icon: Int,
    isBackWhite: Boolean,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(if (isBackWhite) MaterialTheme.colors.onSurface else Color.Transparent)
            .clickable {
                onClick()
            }
    ) {
        Image(painter = painterResource(icon),
            contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(50.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

private fun reCreate(context: Activity) {
    context.startActivity(Intent(context, MainActivity::class.java))
    context.finish()
}
