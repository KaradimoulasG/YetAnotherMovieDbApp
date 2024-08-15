package com.example.yetanothermoviedbapp.data.remote

import com.example.yetanothermoviedbapp.data.remote.dto.showsDto.ShowsDtoItem
import com.example.yetanothermoviedbapp.data.remote.dto.singleShowDto.ShowDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface TvMazeApi {

    @GET("/shows")
    suspend fun getShows() : List<ShowsDtoItem>

    @GET("/shows/{id}")
    suspend fun getShowDetails(
        @Path("id") id: Int
    ) : ShowDetailsDto
}