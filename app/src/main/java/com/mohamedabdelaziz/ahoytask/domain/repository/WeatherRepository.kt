package com.mohamedabdelaziz.ahoytask.domain.repository

import com.mohamedabdelaziz.ahoytask.core.utils.commons.NetworkResult
import com.mohamedabdelaziz.ahoytask.domain.model.ForecastResponse
import com.mohamedabdelaziz.ahoytask.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeather(city: String,appId:String,unit:String): Flow<NetworkResult<WeatherResponse>>
    suspend fun getWeatherLatLang(lat: Double, lng: Double, appId: String, unit: String): Flow<NetworkResult<WeatherResponse>>
    suspend fun getForecastByLatLng(lat: Double, lng: Double, appId: String, unit: String): Flow<NetworkResult<ForecastResponse>>

}