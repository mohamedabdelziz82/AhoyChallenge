package com.mohamedabdelaziz.ahoytask.presentation.di

import com.mohamedabdelaziz.ahoytask.data.local.dao.WeatherDao
import com.mohamedabdelaziz.ahoytask.data.local.dp.WeatherDataBase
import com.mohamedabdelaziz.ahoytask.data.repository.FavouriteRepositoryLocalImp
import com.mohamedabdelaziz.ahoytask.domain.repository.FavouriteRepositoryLocal
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.Module
import dagger.Provides

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    @Singleton
    fun provideDao(weatherDataBase: WeatherDataBase): WeatherDao = weatherDataBase.weatherDao()

    @Singleton
    @Provides
    fun provideFavouriteRepositoryLocal(weatherDao: WeatherDao): FavouriteRepositoryLocal =
        FavouriteRepositoryLocalImp(weatherDao = weatherDao)


}