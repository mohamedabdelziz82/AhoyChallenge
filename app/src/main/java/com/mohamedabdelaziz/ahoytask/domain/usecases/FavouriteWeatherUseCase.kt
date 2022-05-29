package com.mohamedabdelaziz.ahoytask.domain.usecases

import com.mohamedabdelaziz.ahoytask.domain.model.WeatherResponse
import com.mohamedabdelaziz.ahoytask.domain.repository.FavouriteRepositoryLocal
 import javax.inject.Inject

class FavouriteWeatherUseCase  @Inject constructor(private var repository: FavouriteRepositoryLocal){
    suspend fun insertFavourite(weather:WeatherResponse){
        repository.insertFavourite(weather)
    }
    suspend fun invokeFavourite()=repository.getFavouriteList()
}