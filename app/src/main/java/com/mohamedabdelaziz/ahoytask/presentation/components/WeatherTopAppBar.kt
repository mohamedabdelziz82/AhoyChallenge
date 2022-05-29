package com.mohamedabdelaziz.ahoytask.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedabdelaziz.ahoytask.R
import com.mohamedabdelaziz.ahoytask.core.ui.theme.Avenir
import com.mohamedabdelaziz.ahoytask.core.ui.theme.Secondary

@Composable
fun WeatherTopAppBar(
    title: String,
    onFavoriteIconClick: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,
    ) {
        Row(Modifier.fillMaxWidth()
                ,verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .fillMaxWidth(.6f),
                textAlign = TextAlign.End,
                text = title,
                fontFamily = Avenir,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Secondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Box(modifier = Modifier.fillMaxWidth()
                .padding(end = 20.dp)
            ) {
                if (onFavoriteIconClick!=null)
                Image(
                    modifier = Modifier
                         .align(Alignment.CenterEnd)
                        .clickable { onFavoriteIconClick?.invoke() },
                    painter = painterResource(
                        id = R.drawable.ic_favorite
                    ),
                    contentDescription = stringResource(id = R.string.lbl_favourite)
                )
            }
        }

    }
}