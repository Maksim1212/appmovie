package com.example.appmovie.movie.domaim.home.usecase

import com.example.appmovie.movie.common.Film
import com.example.appmovie.movie.data.repository.repository.FilmRepository

class GetPopularFilms(private val filmRepository: FilmRepository) {

    operator fun invoke(): List<Film> {
        return FilmRepository().getPopularFilms()
    }
}
