package com.example.yetanothermoviedbapp.data.repository

import com.example.yetanothermoviedbapp.data.persistence.ShowsDao
import com.example.yetanothermoviedbapp.data.remote.TvMazeApi
import com.example.yetanothermoviedbapp.data.remote.dto.showsDto.ShowsDtoItem
import com.example.yetanothermoviedbapp.data.remote.dto.singleShowDto.ShowDetailsDto
import com.example.yetanothermoviedbapp.domain.repository.ShowsRepo

class ShowsRepoImpl(
    private val api: TvMazeApi,
    private val dao: ShowsDao
) : ShowsRepo {

    override suspend fun getShowsList() =
        api.getShows()

    override suspend fun getShowDetails(showId: Int) =
        api.getShowDetails(showId)
}