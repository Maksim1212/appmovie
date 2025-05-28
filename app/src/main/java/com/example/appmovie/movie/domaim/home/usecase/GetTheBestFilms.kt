package com.example.appmovie.movie.domaim.home.usecase

import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.CategoriesFilmModelConverter
import com.example.appmovie.movie.domaim.home.entity.CategoriesFilmEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTheBestFilms(private val filmRepository: FilmRepository) {

    operator fun invoke(): Flow<List<CategoriesFilmEntity>> =
        filmRepository.getTheBestFilms().map { list ->
            list.map {
                CategoriesFilmModelConverter.convertCategoriesFilmsModelToEntity(it)
            }
        }
}
