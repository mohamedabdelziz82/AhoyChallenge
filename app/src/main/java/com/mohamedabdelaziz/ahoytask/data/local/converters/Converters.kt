package com.mohamedabdelaziz.ahoytask.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mohamedabdelaziz.ahoytask.domain.model.*
import java.util.ArrayList
class Converters {
    @TypeConverter
    fun fromCoordToGson(anyList: Coord): String {
        return Gson().toJson(anyList)
    }

    @TypeConverter
    fun fromGsonToCoord(anyString: String?): Coord {
        val listType = object : TypeToken<Coord>() {}.type
        return Gson().fromJson(anyString, listType)
    }


    @TypeConverter
    fun fromWeatherToGsonList(anyList: ArrayList<Weather>): String {
        return Gson().toJson(anyList)
    }


    @TypeConverter
    fun fromGsonToWeatherList(anyString: String?): ArrayList<Weather> {
        val listType = object : TypeToken<ArrayList<Weather>>() {}.type
        return Gson().fromJson(anyString, listType)

    }

    @TypeConverter
    fun fromMainToGson(anyList: Main): String {
        return Gson().toJson(anyList)
    }

    @TypeConverter
    fun fromGsonToMain(anyString: String?): Main {
        val listType = object : TypeToken<Main>() {}.type
        return Gson().fromJson(anyString, listType)
    }

    @TypeConverter
    fun fromWindToGson(anyList: Wind): String {
        return Gson().toJson(anyList)
    }

    @TypeConverter
    fun fromGsonToWind(anyString: String?): Wind {
        val listType = object : TypeToken<Wind>() {}.type
        return Gson().fromJson(anyString, listType)

    }

    @TypeConverter
    fun fromCloudsToGson(anyList: Clouds): String {
        return Gson().toJson(anyList)
    }

    @TypeConverter
    fun fromGsonToClouds(anyString: String?): Clouds {
        val listType = object : TypeToken<Clouds>() {}.type
        return Gson().fromJson(anyString, listType)

    }

    @TypeConverter
    fun fromSysToGson(anyList: Sys): String {
        return Gson().toJson(anyList)
    }

    @TypeConverter
    fun fromSysToGson(anyString: String?): Sys {
        val listType = object : TypeToken<Sys>() {}.type
        return Gson().fromJson(anyString, listType)

    }
}