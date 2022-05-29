package com.mohamedabdelaziz.ahoytask.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
fun WeatherDetails(weatherResponse:WeatherResponse,temp:MutableState<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 12.dp)
        ) {
            Text(
                textAlign = TextAlign.Start,
                text = weatherResponse.name + " , " + weatherResponse.main?.temp +" "+temp.value,
                fontFamily = Avenir,
                fontWeight = FontWeight.W900,
                fontSize = 14.sp,
                color = DarkNeutral1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Card(
            elevation = 10.dp,
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.White,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                WeatherRaw(
                    image = R.drawable.ic_high_temperature,
                    text = stringResource(id = R.string.lbl_high_temperature) + " " + weatherResponse.main?.tempMax+" "+temp.value,
                )


                WeatherRaw(
                    image = R.drawable.ic_low_temperature,
                    text = stringResource(id = R.string.lbl_low_temperature) + " " + weatherResponse.main?.tempMin+" "+temp.value,
                )

                WeatherRaw(
                    image = R.drawable.ic_wind,
                    text = stringResource(id = R.string.lbl_wind) + " " + weatherResponse.wind?.speed
                )

                WeatherRaw(
                    image = R.drawable.ic_pressure,
                    text = stringResource(id = R.string.lbl_pressure) + " " + weatherResponse.main?.pressure
                )

                WeatherRaw(
                    image = R.drawable.ic_humidity,
                    text = stringResource(id = R.string.lbl_humidity) + " " + weatherResponse.main?.humidity
                )

                WeatherRaw(
                    image = R.drawable.ic_sunrise,
                    text = stringResource(id = R.string.lbl_sunrise) + " " + weatherResponse.sys?.sunrise
                )

                WeatherRaw(
                    image = R.drawable.ic_sunset,
                    text = stringResource(id = R.string.lbl_sunset) + " " + weatherResponse.sys?.sunset
                )

            }
        }

    }
}