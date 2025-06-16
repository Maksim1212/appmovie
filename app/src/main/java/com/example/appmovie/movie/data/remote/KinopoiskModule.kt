package com.example.appmovie.movie.data.remote

import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.usecase.GetFilmByGenreUseCase
import com.example.appmovie.movie.domaim.home.usecase.GetTopRankedFilmsUseCase
import dagger.Module
import dagger.Provides

@Module
class KinopoiskModule {

    @Provides
    fun provideFilmRepository(
        kinopoiskApi: KinopoiskApi
    ): FilmRepository = FilmRepository(kinopoiskApi)

    @Provides
    fun provideGetTopRankedFilmsUseCase(
        filmRepository: FilmRepository
    ): GetTopRankedFilmsUseCase = GetTopRankedFilmsUseCase(filmRepository)


    @Provides
    fun provideGetFilmByGenreUseCase(
        filmRepository: FilmRepository
    ): GetFilmByGenreUseCase = GetFilmByGenreUseCase(filmRepository)


}
