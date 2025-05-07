package com.example.appmovie.movie.data.repository.repository

import com.example.appmovie.movie.common.Film
import com.example.appmovie.movie.data.repository.FilmStorage

class FilmRepository {

    fun getSelectedFilms(): List<Film> = FilmStorage.selectedFilms

    fun getTopRankedFilms(): List<Film> = FilmStorage.topRankedFilms

    fun getPopularFilms(): List<Film> = FilmStorage.popularFilms

    fun getRecommendedFilms(): List<Film> = FilmStorage.recommendedFilms

}
