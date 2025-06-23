package com.example.appmovie.movie.data.di

import com.example.appmovie.movie.presentation.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ModelModule {

    @Binds
    @IntoMap
    @ClassKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): HomeViewModel
}
