package com.example.yetanothermoviedbapp.domain.repository

import com.example.yetanothermoviedbapp.data.remote.dto.showsDto.ShowsDtoItem
import com.example.yetanothermoviedbapp.data.remote.dto.singleShowDto.ShowDetailsDto
import com.example.yetanothermoviedbapp.domain.models.ShowsListItem
import kotlinx.coroutines.flow.Flow

interface ShowsRepo {

    suspend fun getShowsList(shouldRefresh: Boolean) : List<ShowsListItem>

    suspend fun getShowDetails(showId: Int) : ShowDetailsDto
}