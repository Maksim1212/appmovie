package com.example.appmovie.movie.data.repository.repository

import com.example.appmovie.movie.data.FilmModel
import com.example.appmovie.movie.data.PopularFilmsModel
import com.example.appmovie.movie.data.RankedFilmModel
import com.example.appmovie.movie.data.RecommendedFilmsModel
import com.example.appmovie.movie.data.repository.FilmStorage

class FilmRepository {

    fun getSelectedFilms(): List<FilmModel> = FilmStorage.selectedFilms

    fun getTopRankedFilms(): List<RankedFilmModel> = FilmStorage.topRankedFilms

    fun getPopularFilms(): ArrayList<PopularFilmsModel> = FilmStorage.popularFilms

    fun getRecommendedFilms(): List<RecommendedFilmsModel> = FilmStorage.recommendedFilms

}
