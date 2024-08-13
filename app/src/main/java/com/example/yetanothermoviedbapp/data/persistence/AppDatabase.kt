package com.example.yetanothermoviedbapp.data.persistence

import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {
    abstract val showsDao: ShowsDao
}