package com.example.yetanothermoviedbapp.data.remote.dto.commonDtos

data class NetworkDto(
    val country: CountryDto,
    val id: Int,
    val name: String,
    val officialSite: String
)