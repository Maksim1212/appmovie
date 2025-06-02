package com.example.appmovie.movie.data.repository.repository

import com.example.appmovie.movie.data.FilmModel
import com.example.appmovie.movie.data.CategoriesFilmsModel
import com.example.appmovie.movie.data.remote.KinopoiskApi
import com.example.appmovie.movie.data.remote.model.CollectionsResponse
import com.example.appmovie.movie.data.repository.FilmStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilmRepository(
    private val kinopoiskApi: KinopoiskApi
) {

    fun getSelectedFilms(): List<FilmModel> = FilmStorage.selectedFilms

    fun getTopRankedFilms(): Flow<CollectionsResponse> = flow {
        emit(kinopoiskApi.getCollectionsRankedFilms(type = "TOP_POPULAR_ALL", page = 1))
    }

    fun getPopularFilms(): Flow<List<CategoriesFilmsModel>> = flow {
        emit(FilmStorage.popularFilms)
    }

    fun getRecommendedFilms(): Flow<List<CategoriesFilmsModel>> = flow {
        emit(FilmStorage.recommendedFilms)
    }

    fun getNewFilms(): Flow<List<CategoriesFilmsModel>> = flow {
        emit(FilmStorage.newFilms)
    }

    fun getTheBestFilms(): Flow<List<CategoriesFilmsModel>> = flow {
        emit(FilmStorage.theBestFilms)
    }
}
