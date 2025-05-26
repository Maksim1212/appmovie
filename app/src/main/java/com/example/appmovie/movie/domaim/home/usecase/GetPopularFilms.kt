package com.example.appmovie.movie.domaim.home.usecase

import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.CategoriesFilmModelConverter
import com.example.appmovie.movie.domaim.home.entity.CategoriesFilmEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPopularFilms(private val filmRepository: FilmRepository) {

    operator fun invoke(): Flow<CategoriesFilmEntity> = flow {
        filmRepository.getPopularFilms().forEach { model ->
            emit(CategoriesFilmModelConverter.convertCategoriesFilmsModelToEntity(model))
        }
    }
}
