package com.example.appmovie.movie.domaim.home.usecase

import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.CategoriesFilmModelConverter
import com.example.appmovie.movie.domaim.home.entity.CategoriesFilmEntity

class GetRecommendedFilms(private val filmRepository: FilmRepository) {

    operator fun invoke(): List<CategoriesFilmEntity> {
        val models = filmRepository.getRecommendedFilms()
        return models.map {
            CategoriesFilmModelConverter.convertCategoriesFilmsModelToEntity(it)
        }
    }
}
