package com.example.yetanothermoviedbapp.domain.models

import com.example.yetanothermoviedbapp.data.remote.dto.singleShowDto.ShowDetailsDto

data class ShowDetails(
    val id: Int,
    val name: String,
    val image: Image,
    val rating: Rating,
    val runtime: Int,
    val summary: String
)
