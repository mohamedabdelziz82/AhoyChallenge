package com.mohamedabdelaziz.ahoytask.data.local.dp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohamedabdelaziz.ahoytask.data.local.converters.Converters
import com.mohamedabdelaziz.ahoytask.data.local.dao.WeatherDao
import com.mohamedabdelaziz.ahoytask.domain.model.WeatherResponse

@Database(entities = [WeatherResponse::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}