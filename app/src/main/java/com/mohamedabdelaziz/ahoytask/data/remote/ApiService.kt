package com.mohamedabdelaziz.ahoytask.data.remote

import com.mohamedabdelaziz.ahoytask.domain.model.ForecastResponse
import com.mohamedabdelaziz.ahoytask.domain.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String, @Query("appid") appid: String,
        @Query("units") unit: String
    ): Response<WeatherResponse>

    @GET("weather")
    suspend fun getWeatherByLatLng(
        @Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appid") appid: String,
        @Query("units") unit: String
    ): Response<WeatherResponse>

    @GET("forecast/daily")
    suspend fun getWeatherForecastByLatLng(
        @Query("lat") lat:Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") unit: String
    ): Response<ForecastResponse>

}