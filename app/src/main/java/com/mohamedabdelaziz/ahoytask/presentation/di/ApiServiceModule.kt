package com.mohamedabdelaziz.ahoytask.presentation.di
import com.mohamedabdelaziz.ahoytask.data.remote.ApiService
import com.mohamedabdelaziz.ahoytask.data.repository.WeatherRepositoryImp
import com.mohamedabdelaziz.ahoytask.domain.repository.WeatherRepository
import dagger.hilt.InstallIn
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {
    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideWeatherRepository(apiService: ApiService): WeatherRepository =
        WeatherRepositoryImp(apiService)
}