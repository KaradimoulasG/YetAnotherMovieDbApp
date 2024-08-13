package com.example.yetanothermoviedbapp.data.remote.dto.singleShowDto

import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.PreviousEpisodeDto
import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.SelfDto

data class LinksDto(
    val previousepisode: PreviousEpisodeDto,
    val self: SelfDto
)