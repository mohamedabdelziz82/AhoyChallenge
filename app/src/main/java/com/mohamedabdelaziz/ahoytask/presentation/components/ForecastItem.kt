package com.mohamedabdelaziz.ahoytask.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mohamedabdelaziz.ahoytask.R
import com.mohamedabdelaziz.ahoytask.core.utils.Common
import com.mohamedabdelaziz.ahoytask.core.utils.Common.Companion.getTemperatureType
import com.mohamedabdelaziz.ahoytask.domain.model.ForecastList
import com.mohamedabdelaziz.ahoytask.presentation.WeatherViewModel

@Composable
fun ForecastItem(forecastList: ForecastList,viewModel: WeatherViewModel= hiltViewModel()) {
    val temp = remember { mutableStateOf("") }
    temp.value = getTemperatureType(viewModel.temperature)
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(8.dp)


    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            WeatherRaw(
                image = R.drawable.ic_date,
                text = Common.convertUnixToDate(forecastList.dt!!.toLong()).toString()
            )
            WeatherRaw(
                image = R.drawable.ic_desc,
                text = stringResource(id = R.string.lbl_desc) + " " + forecastList.weather[0].description!!
            )
            WeatherRaw(
                image = R.drawable.ic_low_temperature,
                text = stringResource(id = R.string.lbl_temperature) + " " + forecastList.temp!!.day.toString()+" "+temp.value,
            )
        }
    }
}