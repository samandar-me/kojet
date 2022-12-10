package com.sdk.kojetdsr.presentation.bottom.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.sdk.kojetdsr.R
import com.sdk.kojetdsr.presentation.component.BoxItem
import com.sdk.kojetdsr.ui.theme.Orange
import androidx.compose.ui.unit.dp as dp

@Composable
fun SettingsScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        TopAppBar(
            title = {
                Text(text = "Settings")
            },
            backgroundColor = Orange
        )
        BoxItem(
            modifier = Modifier.padding(PaddingValues(4.dp))
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(5.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_language_24),
                    contentDescription = "Language"
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = stringResource(id = R.string.language),
                    fontSize = 17.sp
                )
            }
            Spacer(modifier = Modifier.height(3.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                ) {
                    Image(painter = painterResource(id = R.drawable.uk),
                        contentDescription = "",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(50.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                ) {
                    Image(painter = painterResource(id = R.drawable.rus),
                        contentDescription = "",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(50.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        BoxItem(
            modifier = Modifier.padding(PaddingValues(4.dp))
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(5.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_color_lens_24),
                    contentDescription = "Language"
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = stringResource(id = R.string.theme),
                    fontSize = 17.sp
                )
            }
            Spacer(modifier = Modifier.height(3.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_baseline_auto_awesome_24),
                        contentDescription = "",
                        modifier = Modifier
                            .size(60.dp)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                ) {
                    Image(painter = painterResource(id = R.drawable.day),
                        contentDescription = "",
                        modifier = Modifier
                            .size(60.dp)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                ) {
                    Image(painter = painterResource(id = R.drawable.night),
                        contentDescription = "",
                        modifier = Modifier
                            .size(60.dp)
                    )
                }
            }
        }
    }
}