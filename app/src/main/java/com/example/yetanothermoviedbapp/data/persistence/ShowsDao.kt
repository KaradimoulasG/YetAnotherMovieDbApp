package com.example.yetanothermoviedbapp.data.persistence

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.yetanothermoviedbapp.domain.models.ShowsListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShowsDao {

    @Upsert
    fun saveShowsList(list: List<ShowsListItem>)

    @Query("SELECT * FROM ShowsListItem")
    fun getSavedShows(): List<ShowsListItem>
}