package com.example.appmovie.movie.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appmovie.movie.presentation.home.Factory
import com.example.appmovie.movie.presentation.home.HomeViewModel
import com.example.appmovie.movie.presentation.infofilm.InfoFilmViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: Factory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindMyViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InfoFilmViewModel::class)
    fun bindInfoViewModel(viewModel: InfoFilmViewModel): ViewModel
}
