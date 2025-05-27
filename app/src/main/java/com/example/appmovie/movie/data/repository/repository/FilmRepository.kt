package com.example.appmovie.movie.data.repository.repository

import com.example.appmovie.movie.data.FilmModel
import com.example.appmovie.movie.data.RankedFilmModel
import com.example.appmovie.movie.data.CategoriesFilmsModel
import com.example.appmovie.movie.data.repository.FilmStorage

class FilmRepository {

    fun getSelectedFilms(): List<FilmModel> = FilmStorage.selectedFilms

    fun getTopRankedFilms(): List<RankedFilmModel> = FilmStorage.topRankedFilms

    fun getPopularFilms(): ArrayList<CategoriesFilmsModel> = FilmStorage.popularFilms

    fun getRecommendedFilms(): List<CategoriesFilmsModel> = FilmStorage.recommendedFilms

    fun getNewFilms(): List<CategoriesFilmsModel> = FilmStorage.newFilms

    fun getTheBestFilms(): List<CategoriesFilmsModel> = FilmStorage.theBestFilms
}
