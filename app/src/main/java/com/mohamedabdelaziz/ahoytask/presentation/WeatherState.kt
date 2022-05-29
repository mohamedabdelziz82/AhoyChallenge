package com.mohamedabdelaziz.ahoytask.presentation

import com.mohamedabdelaziz.ahoytask.domain.model.WeatherResponse


data class WeatherState(
    var weather: WeatherResponse = WeatherResponse(),
    var error: String? = null,
    var isLoading: Boolean = false
)