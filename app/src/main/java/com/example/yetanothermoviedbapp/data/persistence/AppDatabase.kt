package com.example.yetanothermoviedbapp.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yetanothermoviedbapp.domain.models.ShowsListItem

@Database(entities = [ShowsListItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val showsDao: ShowsDao
}