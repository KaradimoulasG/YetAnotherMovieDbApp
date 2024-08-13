package com.example.yetanothermoviedbapp.data.remote.dto.singleShowDto

import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.ExternalsDto
import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.ImageDto
import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.NetworkDto
import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.RatingDto
import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.ScheduleDto
import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.DvdCountryDto
import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.WebChannelDto

data class ShowDetailsDto(
    val links: LinksDto,
    val averageRuntime: Int,
    val dvdCountry: DvdCountryDto,
    val ended: String,
    val externals: ExternalsDto,
    val genres: List<String>,
    val id: Int,
    val image: ImageDto,
    val language: String,
    val name: String,
    val network: NetworkDto,
    val officialSite: String,
    val premiered: String,
    val rating: RatingDto,
    val runtime: Int,
    val schedule: ScheduleDto,
    val status: String,
    val summary: String,
    val type: String,
    val updated: Int,
    val url: String,
    val webChannel: WebChannelDto,
    val weight: Int
)