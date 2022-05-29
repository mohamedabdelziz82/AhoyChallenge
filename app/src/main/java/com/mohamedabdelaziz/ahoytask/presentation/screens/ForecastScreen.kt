package com.mohamedabdelaziz.ahoytask.presentation.screens

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mohamedabdelaziz.ahoytask.core.base.BaseShimmer
import com.mohamedabdelaziz.ahoytask.core.ui.theme.LightNeutral1
import com.mohamedabdelaziz.ahoytask.presentation.WeatherViewModel
import com.mohamedabdelaziz.ahoytask.presentation.components.ForecastItem
import com.mohamedabdelaziz.ahoytask.presentation.components.WeatherContentShimmer
import com.mohamedabdelaziz.ahoytask.presentation.components.WeatherTopAppBar

@RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
@Composable
fun ForecastScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val forecastState = remember { viewModel.stateForecast }
    val loadingState = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(true) {
        viewModel.getLocationAndFetchWeather(context = context, true)
        loadingState.value = true
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 60.dp)
            .background(color = LightNeutral1)
    ) {
        if (forecastState.value.forecast.city!!.name != null)
            WeatherTopAppBar(
                title = forecastState.value.forecast.city!!.name.toString(),
                )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(forecastState.value.forecast.list, key = { it.dt!! })
            {
                    item ->
                ForecastItem(forecastList = item)
             }
        }
        if (loadingState.value) {
            repeat(7) {
                BaseShimmer {
                    WeatherContentShimmer(brush = it)
                }
            }
        }
    }


}