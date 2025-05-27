package com.example.appmovie.movie.data.repository.repository

import com.example.appmovie.movie.data.FilmModel
import com.example.appmovie.movie.data.RankedFilmModel
import com.example.appmovie.movie.data.CategoriesFilmsModel
import com.example.appmovie.movie.data.repository.FilmStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilmRepository {

    fun getSelectedFilms(): List<FilmModel> = FilmStorage.selectedFilms

    fun getTopRankedFilms(): Flow<List<RankedFilmModel>> = flow {
        emit(FilmStorage.topRankedFilms)
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
