package com.example.yetanothermoviedbapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.yetanothermoviedbapp.data.persistence.ShowTypeConverters
import com.example.yetanothermoviedbapp.data.remote.dto.showsDto.ShowsDtoItem

@Entity
data class ShowsListItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: Image,
    val name: String,
    val rating: Rating
)