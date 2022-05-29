package com.mohamedabdelaziz.ahoytask.data.repository

import com.mohamedabdelaziz.ahoytask.data.local.dao.WeatherDao
import com.mohamedabdelaziz.ahoytask.domain.model.WeatherResponse
import com.mohamedabdelaziz.ahoytask.domain.repository.FavouriteRepositoryLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouriteRepositoryLocalImp @Inject constructor(
    private var weatherDao: WeatherDao
)  :FavouriteRepositoryLocal{
    override suspend fun insertFavourite(response: WeatherResponse) {
        weatherDao.insertFavourite(response)
     }
    override suspend fun getFavouriteList(): Flow<List<WeatherResponse>> = weatherDao.getAllFavourites()



}