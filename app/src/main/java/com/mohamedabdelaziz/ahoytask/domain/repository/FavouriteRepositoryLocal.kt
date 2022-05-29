package com.mohamedabdelaziz.ahoytask.domain.repository

import com.mohamedabdelaziz.ahoytask.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface FavouriteRepositoryLocal {
    suspend fun insertFavourite(response: WeatherResponse)
    suspend fun getFavouriteList(): Flow<List<WeatherResponse>>

}