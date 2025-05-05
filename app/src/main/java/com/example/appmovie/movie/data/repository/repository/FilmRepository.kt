package com.example.appmovie.movie.data.repository.repository

import com.example.appmovie.movie.common.Film
import com.example.appmovie.movie.data.repository.FilmStorage

class FilmRepository {

    fun getSelectedFilms(): List<Film> = FilmStorage.selectedFilms

    fun getTopTenFilms(): List<Film> = FilmStorage.topTenFilms

    fun getPopularMovies(): List<Film> = FilmStorage.popularMovies

    fun getRecommendedMovies(): List<Film> = FilmStorage.recommendedMovies

}
