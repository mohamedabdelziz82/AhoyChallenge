package com.mohamedabdelaziz.ahoytask.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mohamedabdelaziz.ahoytask.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {
    @Insert
      fun insertFavourite(weatherResponse: WeatherResponse)
    @Query("select * from weather_table")
      fun getAllFavourites(): Flow<List<WeatherResponse>>

}