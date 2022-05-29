package com.mohamedabdelaziz.ahoytask.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.mohamedabdelaziz.ahoytask.core.utils.Common
import com.mohamedabdelaziz.ahoytask.presentation.WeatherViewModel

@Composable
fun WeatherContent(viewModel: WeatherViewModel) {
    val weatherResponse = remember {
        viewModel.stateWeather.value.weather
    }
    val temp = remember { mutableStateOf("") }
    temp.value = Common.getTemperatureType(viewModel.temperature)


    if (weatherResponse.weather.isNotEmpty()) {
        WeatherDetails(weatherResponse = weatherResponse, temp = temp)
    }


}