package com.mohamedabdelaziz.ahoytask.domain.usecases

import com.mohamedabdelaziz.ahoytask.core.utils.commons.NetworkResult
import com.mohamedabdelaziz.ahoytask.domain.model.ForecastResponse
import com.mohamedabdelaziz.ahoytask.domain.model.WeatherResponse
import com.mohamedabdelaziz.ahoytask.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherUseCase @Inject constructor(private var repository: WeatherRepository){
    suspend fun invokeWeather(city: String,appId:String,unit:String): Flow<NetworkResult<WeatherResponse>> =repository.getWeather(city,appId,unit)
    suspend fun invokeWeatherLatLang(lat: Double, lng: Double, appId: String, unit: String): Flow<NetworkResult<WeatherResponse>> =repository.getWeatherLatLang(lat = lat, lng = lng,appId,unit)
    suspend fun invokeForecastByLatLng(lat: Double, lng: Double, appId: String, unit: String): Flow<NetworkResult<ForecastResponse>> =repository.getForecastByLatLng(lat = lat, lng = lng,appId,unit)
}