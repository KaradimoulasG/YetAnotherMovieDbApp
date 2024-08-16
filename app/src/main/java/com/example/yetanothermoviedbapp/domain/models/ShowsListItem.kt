package com.example.yetanothermoviedbapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.yetanothermoviedbapp.data.persistence.ShowTypeConverters

@Entity
data class ShowsListItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @field:TypeConverters(ShowTypeConverters::class)
    val image: Image,
    val name: String,
    val rating: Rating
)
