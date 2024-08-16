package com.example.yetanothermoviedbapp.data.persistence

import androidx.room.TypeConverter
import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.ImageDto
import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.RatingDto
import com.example.yetanothermoviedbapp.domain.models.Image
import com.example.yetanothermoviedbapp.domain.models.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShowTypeConverters {

    private val gson = Gson()

    @TypeConverter
    fun fromImage(image: Image? = null) = gson.toJson(image)

    @TypeConverter
    fun toImage(json: String) = gson.fromJson(json, Image::class.java)


    @TypeConverter
    fun fromRating(rating: Rating? = null) = gson.toJson(rating)

    @TypeConverter
    fun toRating(json: String) = gson.fromJson(json, Rating::class.java)

//    @TypeConverter
//    fun fromImage(image: ImageDto) = gson.toJson(image)
//
//    @TypeConverter
//    fun toImage(json: String): ImageDto {
//        val type = object : TypeToken<ImageDto>() {}.type
//        return gson.fromJson(json, type)
//    }
}