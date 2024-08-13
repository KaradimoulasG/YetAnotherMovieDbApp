package com.example.yetanothermoviedbapp.di

import com.example.yetanothermoviedbapp.common.Constants.BASE_URL
import com.example.yetanothermoviedbapp.data.remote.TvMazeApi
import org.koin.dsl.module
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val ApiModule = module {

    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val originalHttpUrl = chain.request().url
//                val newUrl = originalHttpUrl.newBuilder()
//                    .addQueryParameter("api_key", API_KEY).build()
                val request: Request =
                    chain.request().newBuilder()
//                        .url(newUrl)
                        .get()
                        .build()
                chain.proceed(request)
            }
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TvMazeApi::class.java)
    }
}