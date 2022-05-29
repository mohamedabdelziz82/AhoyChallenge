package com.mohamedabdelaziz.ahoytask.core.di

import android.app.Application
import androidx.room.Room
import com.mohamedabdelaziz.ahoytask.data.local.dp.WeatherDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDB(application: Application): WeatherDataBase = Room.databaseBuilder(application, WeatherDataBase::class.java, "weather_db")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}