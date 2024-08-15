package com.example.yetanothermoviedbapp.di

import com.example.yetanothermoviedbapp.data.repository.ShowsRepoImpl
import com.example.yetanothermoviedbapp.domain.repository.ShowsRepo
import com.example.yetanothermoviedbapp.domain.use_case.GetShowsListUseCase
import com.example.yetanothermoviedbapp.presentation.showsList.showsListViewModel.ShowsListViewModel
import org.koin.dsl.module

val ShowsModule = module {

    single<ShowsRepo> { ShowsRepoImpl(get()) }

    single { GetShowsListUseCase(get()) }

    single { ShowsListViewModel(get()) }
}