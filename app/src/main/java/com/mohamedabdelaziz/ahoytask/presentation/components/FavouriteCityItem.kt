package com.mohamedabdelaziz.ahoytask.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedabdelaziz.ahoytask.R
import com.mohamedabdelaziz.ahoytask.core.ui.theme.Avenir
import com.mohamedabdelaziz.ahoytask.core.ui.theme.DarkNeutral1
import com.mohamedabdelaziz.ahoytask.domain.model.WeatherResponse

@Composable
fun FavouriteCityItem(weatherResponse: WeatherResponse, onItemClick: (WeatherResponse) -> Unit ) {
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(5.dp)
            .clickable { onItemClick(weatherResponse)}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 5.dp, start = 5.dp),
                textAlign = TextAlign.Start,
                text = weatherResponse.name.toString(),
                fontFamily = Avenir,
                fontWeight = FontWeight.W900,
                fontSize = 16.sp,
                color = DarkNeutral1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp, bottom = 5.dp),
            ) {
                Image(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .width(24.dp)
                        .height(24.dp),
                    painter = painterResource(R.drawable.ic_high_temperature),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp),
                    textAlign = TextAlign.Start,
                    text = weatherResponse.main!!.temp.toString(),
                    fontFamily = Avenir,
                    fontWeight = FontWeight.W900,
                    fontSize = 14.sp,
                    color = DarkNeutral1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}