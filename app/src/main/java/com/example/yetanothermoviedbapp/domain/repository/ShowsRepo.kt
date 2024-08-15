package com.example.yetanothermoviedbapp.domain.repository

import com.example.yetanothermoviedbapp.data.remote.dto.showsDto.ShowsDtoItem
import com.example.yetanothermoviedbapp.data.remote.dto.singleShowDto.ShowDetailsDto

interface ShowsRepo {

    suspend fun getShowsList() : List<ShowsDtoItem>

    suspend fun getShowDetails(showId: Int) : ShowDetailsDto
}