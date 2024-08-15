package com.example.yetanothermoviedbapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShowsListItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: Image,
    val name: String,
    val rating: Rating
)
