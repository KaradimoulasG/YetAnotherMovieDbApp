package com.example.yetanothermoviedbapp.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.yetanothermoviedbapp.R
import com.example.yetanothermoviedbapp.databinding.ActivityMainBinding
import com.example.yetanothermoviedbapp.di.ShowsModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        loadKoinModules(ShowsModule)
    }


    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(ShowsModule)
    }

}