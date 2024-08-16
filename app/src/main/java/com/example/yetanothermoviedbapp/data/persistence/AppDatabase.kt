package com.example.yetanothermoviedbapp.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.yetanothermoviedbapp.domain.models.ShowsListItem

@Database(entities = [ShowsListItem::class], version = 1, exportSchema = false)
@TypeConverters(ShowTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val showsDao: ShowsDao
}