package com.example.yetanothermoviedbapp.di

import androidx.room.Room
import com.example.yetanothermoviedbapp.data.persistence.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val PersistenceModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "appDatabase",
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    single { get<AppDatabase>().showsDao }
}