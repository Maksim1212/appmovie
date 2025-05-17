package com.example.appmovie.movie.domaim.home.usecase

import com.example.appmovie.movie.data.CategoriesFilmsModel
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.entity.CategoriesFilmEntity

class GetPopularFilms(private val filmRepository: FilmRepository) {

    operator fun invoke(): List<CategoriesFilmEntity> {
        val models = filmRepository.getPopularFilms()
        return models.map {
            convertCategoriesFilmsModelToEntity(it)
        }

    }

    private fun convertCategoriesFilmsModelToEntity(
        categoriesFilmsModel: CategoriesFilmsModel
    ): CategoriesFilmEntity = CategoriesFilmEntity(
        id = categoriesFilmsModel.id,
        cover = categoriesFilmsModel.cover
    )

}
