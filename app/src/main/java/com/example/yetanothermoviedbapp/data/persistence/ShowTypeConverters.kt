package com.example.yetanothermoviedbapp.data.persistence

import androidx.room.TypeConverter
import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.ImageDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShowTypeConverters {

    private val gson = Gson()

    @TypeConverter
    fun fromWeather(weather: List<ImageDto>) = gson.toJson(weather)

    @TypeConverter
    fun toWeather(json: String): List<ImageDto> {
        val type = object : TypeToken<List<ImageDto>>() {}.type
        return gson.fromJson(json, type)
    }

//    @TypeConverter
//    fun fromImage(image: ImageDto? = null) = gson.toJson(image)
//
//    @TypeConverter
//    fun toImage(json: String) = gson.fromJson(json, ImageDto::class.java)
}