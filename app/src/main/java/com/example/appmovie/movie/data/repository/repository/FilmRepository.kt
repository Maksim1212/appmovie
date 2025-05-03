package com.example.appmovie.movie.data.repository.repository

import com.example.appmovie.movie.common.Film
import com.example.appmovie.movie.data.repository.FIlmStorage

class FilmRepository {
    fun getSelectedFilms(): List<Film> {
           return FIlmStorage.selected_films
         }
    fun getTopTenFilms(): List<Film> {
        return FIlmStorage.top_10_films
    }
    fun getPopularMmovies(): List<Film> {
        return FIlmStorage.popular_movies
    }
    fun getRecommended_Movies(): List<Film> {
        return FIlmStorage.recommended_movies
    }
}

