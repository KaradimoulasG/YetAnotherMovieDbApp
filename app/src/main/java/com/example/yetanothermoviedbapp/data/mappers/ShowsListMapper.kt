package com.example.yetanothermoviedbapp.data.mappers

import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.ImageDto
import com.example.yetanothermoviedbapp.data.remote.dto.commonDtos.RatingDto
import com.example.yetanothermoviedbapp.data.remote.dto.showsDto.ShowsDtoItem
import com.example.yetanothermoviedbapp.domain.models.Image
import com.example.yetanothermoviedbapp.domain.models.Rating
import com.example.yetanothermoviedbapp.domain.models.ShowsListItem

object ShowsListMapper : Mapper<List<ShowsDtoItem>, List<ShowsListItem>> {

    override fun modelToDomain(model: List<ShowsDtoItem>): List<ShowsListItem> =
        model.map { dto ->
            ShowsListItem(
                id = dto.id,
                image = dto.image.toImage(),
                name = dto.name,
                rating = dto.rating.toRating()
            )
        }


    private fun RatingDto.toRating() =
        Rating(average = average)

    private fun ImageDto.toImage() =
        Image(
            medium = medium,
            original = original
        )


    override fun domainToModel(domainModel: List<ShowsListItem>): List<ShowsDtoItem> {
        TODO("Not yet implemented")
    }

}