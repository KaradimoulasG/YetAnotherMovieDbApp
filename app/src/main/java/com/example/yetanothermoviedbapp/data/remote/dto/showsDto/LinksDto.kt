package com.example.yetanothermoviedbapp.data.remote.dto.showsDto

import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.PreviousEpisodeDto
import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.SelfDto

data class LinksDto(
    val nextepisode: NextEpisodeDto,
    val previousepisode: PreviousEpisodeDto,
    val self: SelfDto
)