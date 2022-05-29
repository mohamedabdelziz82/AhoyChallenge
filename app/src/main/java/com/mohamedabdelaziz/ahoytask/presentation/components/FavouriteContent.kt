package com.mohamedabdelaziz.ahoytask.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mohamedabdelaziz.ahoytask.core.utils.Common
import com.mohamedabdelaziz.ahoytask.domain.model.WeatherResponse
import com.mohamedabdelaziz.ahoytask.presentation.WeatherViewModel

@Composable
fun FavouriteContent(weatherResponseList: List<WeatherResponse>, viewModel: WeatherViewModel) {
    val itemSelected = remember {
        mutableStateOf(WeatherResponse())
    }
    var isWeatherDetailVisible by remember { mutableStateOf(true) }
    val temp = remember { mutableStateOf("") }
    temp.value = Common.getTemperatureType(viewModel.temperature)


    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(weatherResponseList) { item ->
            AnimatedVisibility(visible = isWeatherDetailVisible) {
                FavouriteCityItem(weatherResponse = item, onItemClick = {
                    itemSelected.value = item
                    isWeatherDetailVisible = !isWeatherDetailVisible
                })
            }
        }
        item {
            AnimatedVisibility(visible = !isWeatherDetailVisible) {
                WeatherDetails(itemSelected.value, temp = temp)
            }
        }


    }


}