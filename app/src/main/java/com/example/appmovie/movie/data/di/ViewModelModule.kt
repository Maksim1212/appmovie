package com.example.appmovie.movie.data.di

import androidx.lifecycle.ViewModel
import com.example.appmovie.movie.domaim.home.usecase.GetFilmByGenreUseCase
import com.example.appmovie.movie.domaim.home.usecase.GetTopRankedFilmsUseCase
import com.example.appmovie.movie.presentation.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
class ViewModelModule {

    @IntoMap
    @StringKey("HomeViewModel")
    @Binds
    fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel {
    }

}


