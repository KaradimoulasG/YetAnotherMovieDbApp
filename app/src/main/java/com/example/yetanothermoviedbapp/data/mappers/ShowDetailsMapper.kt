package com.example.yetanothermoviedbapp.data.mappers

import com.example.yetanothermoviedbapp.data.mappers.ShowsListMapper.toImage
import com.example.yetanothermoviedbapp.data.mappers.ShowsListMapper.toRating
import com.example.yetanothermoviedbapp.data.remote.dto.singleShowDto.ShowDetailsDto
import com.example.yetanothermoviedbapp.domain.models.ShowDetails

object ShowDetailsMapper: Mapper<ShowDetailsDto, ShowDetails> {

    override fun modelToDomain(model: ShowDetailsDto) =
        ShowDetails(
            id = model.id,
            name = model.name,
            image = model.image.toImage(),
            rating = model.rating.toRating(),
            runtime = model.runtime,
            summary = model.summary
        )

    override fun domainToModel(domainModel: ShowDetails): ShowDetailsDto { }
}