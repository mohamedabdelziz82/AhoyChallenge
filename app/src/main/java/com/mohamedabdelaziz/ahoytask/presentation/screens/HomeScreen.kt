package com.mohamedabdelaziz.ahoytask.presentation.screens

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mohamedabdelaziz.ahoytask.R
import com.mohamedabdelaziz.ahoytask.core.base.BaseShimmer
import com.mohamedabdelaziz.ahoytask.core.ui.theme.LightNeutral1
import com.mohamedabdelaziz.ahoytask.presentation.WeatherViewModel
import com.mohamedabdelaziz.ahoytask.presentation.components.SearchBar
import com.mohamedabdelaziz.ahoytask.presentation.components.WeatherContent
import com.mohamedabdelaziz.ahoytask.presentation.components.WeatherContentShimmer
import com.mohamedabdelaziz.ahoytask.presentation.components.WeatherTopAppBar
import kotlinx.coroutines.launch

@RequiresPermission(ACCESS_FINE_LOCATION)
@Composable
fun HomeScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherState = remember { viewModel.stateWeather }
    var loadingState = remember {
        mutableStateOf(false)
    }
    var cancelSearchStateVisibility by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    LaunchedEffect(true) {
        viewModel.getLocationAndFetchWeather(context = context, false)
        loadingState.value = true
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LightNeutral1)
    ) {

        if (weatherState.value.weather.weather.isNotEmpty()) {
            viewModel.weatherWork(context,weatherState.value.weather.main!!.temp.toString(),weatherState.value.weather.name!!)
            WeatherTopAppBar(title = weatherState.value.weather.name!!, onFavoriteIconClick = {
                scope.launch { viewModel.addToFavourite(weatherState.value.weather) }
                Toast.makeText(context, R.string.mgs_saved_to_favourite, Toast.LENGTH_SHORT).show()
            })
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {

            item {
                AnimatedVisibility(visible = cancelSearchStateVisibility) {
                    SearchBar(
                        placeholder = stringResource(R.string.lbl_search_cities),
                        isEnabled = false,
                        onClick = {
                            cancelSearchStateVisibility = !cancelSearchStateVisibility
                        },
                    )
                }
            }
            item {
                AnimatedVisibility(visible = !cancelSearchStateVisibility) {
                    SearchBar(placeholder = stringResource(R.string.lbl_search_cities),
                        isEnabled = true,
                        openKeyboard = true,
                        onCancel = {
                            cancelSearchStateVisibility = !cancelSearchStateVisibility
                            if (weatherState.value.weather.weather.isEmpty())
                                viewModel.getLocationAndFetchWeather(context = context)
                        },
                        showClearIcon = true,
                        searchTerm = {
                            viewModel.loadWeatherFromApi(it, "metric")
                        }
                    )
                }
            }
            item {
                if (weatherState.value.weather.weather.isNotEmpty()) {
                    WeatherContent(viewModel = viewModel)
                    loadingState.value = false
                }
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


