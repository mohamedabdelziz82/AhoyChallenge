package com.mohamedabdelaziz.ahoytask.presentation

import com.mohamedabdelaziz.ahoytask.domain.model.ForecastResponse
import com.mohamedabdelaziz.ahoytask.domain.model.WeatherResponse

data class FavouriteState(
    var favouriteList:List<WeatherResponse> = emptyList(),
)