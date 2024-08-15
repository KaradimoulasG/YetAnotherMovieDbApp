package com.example.yetanothermoviedbapp

import android.app.Application
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.yetanothermoviedbapp.di.ApiModule
import com.example.yetanothermoviedbapp.di.PersistenceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MovieDbApplication : Application() {
    override fun onCreate() {
        super.onCreate()


        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@MovieDbApplication)
            modules(
                ApiModule,
                PersistenceModule
            )
        }
    }
}