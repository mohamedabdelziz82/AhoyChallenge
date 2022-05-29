package com.mohamedabdelaziz.ahoytask.data.repository

import com.google.gson.Gson
import com.mohamedabdelaziz.ahoytask.core.utils.commons.ApiErrorResponse
import com.mohamedabdelaziz.ahoytask.core.utils.commons.NetworkResult
import com.mohamedabdelaziz.ahoytask.data.remote.ApiService
import com.mohamedabdelaziz.ahoytask.domain.model.ForecastResponse
import com.mohamedabdelaziz.ahoytask.domain.model.WeatherResponse
import com.mohamedabdelaziz.ahoytask.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : WeatherRepository {
    override suspend fun getWeather(
        city: String,
        appId: String,
        unit: String
    ): Flow<NetworkResult<WeatherResponse>> = flow {
        emit(NetworkResult.Loading)
        val weatherResponse = apiService.getWeatherByCity(city = city, appid = appId, unit = unit)
        if (weatherResponse.isSuccessful) {
            emit(NetworkResult.Success(weatherResponse.body()!!))
        } else {
            emit(
                NetworkResult.Failure(
                    ApiErrorResponse(
                        weatherResponse.message(),
                        weatherResponse.code(),
                        Gson().fromJson(
                            weatherResponse.errorBody()?.string(),
                            ApiErrorResponse::class.java
                        ).desc.toString()
                    )
                )
            )
        }
    }

    override suspend fun getWeatherLatLang(
        lat: Double,
        lng: Double,
        appId: String,
        unit: String
    ): Flow<NetworkResult<WeatherResponse>> = flow {
        emit(NetworkResult.Loading)
        val weatherResponse =
            apiService.getWeatherByLatLng(lat = lat, lon = lng, appid = appId, unit = unit)
        if (weatherResponse.isSuccessful) {
            emit(NetworkResult.Success(weatherResponse.body()!!))
        } else {
            emit(
                NetworkResult.Failure(
                    ApiErrorResponse(
                        weatherResponse.message(),
                        weatherResponse.code(),
                        Gson().fromJson(
                            weatherResponse.errorBody()?.string(),
                            ApiErrorResponse::class.java
                        ).desc.toString()
                    )
                )
            )
        }
    }

    override suspend fun getForecastByLatLng(
        lat: Double,
        lng: Double,
        appId: String,
        unit: String
    ): Flow<NetworkResult<ForecastResponse>> =flow {
        emit(NetworkResult.Loading)
        val forecastResponse =
            apiService.getWeatherForecastByLatLng(lat = lat, lon = lng, appid = appId, unit = unit)
        if (forecastResponse.isSuccessful) {
             emit(NetworkResult.Success(forecastResponse.body()!!))
        } else {
            emit(NetworkResult.Failure(
                    ApiErrorResponse(
                        forecastResponse.message(),
                        forecastResponse.code(),
                        Gson().fromJson(
                            forecastResponse.errorBody()?.string(),
                            ApiErrorResponse::class.java
                        ).desc.toString()
                    )
                )
            )
        }
    }
}