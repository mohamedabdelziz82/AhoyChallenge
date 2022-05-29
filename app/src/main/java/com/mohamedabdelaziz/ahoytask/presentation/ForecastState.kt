package com.mohamedabdelaziz.ahoytask.presentation

import com.mohamedabdelaziz.ahoytask.domain.model.ForecastResponse

data class ForecastState(
    var forecast: ForecastResponse = ForecastResponse(),
    var error: String? = null,
    var isLoading: Boolean = false
)