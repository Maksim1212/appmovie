package com.example.appmovie.movie.domaim.home.usecase

import com.example.appmovie.movie.data.CategoriesFilmsModel
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.entity.PopularFilmsEntity
import com.example.appmovie.movie.domaim.home.entity.TheBestFilmsEntity

class GetTheBestFilms(private val filmRepository: FilmRepository) {

    operator fun invoke(): List<TheBestFilmsEntity> {
        val models = filmRepository.getPopularFilms()
        return models.map {
            convertCategoriesFilmsModelToEntity(it)
        }

    }

    private fun convertCategoriesFilmsModelToEntity(
        categoriesFilmsModel: CategoriesFilmsModel
    ): TheBestFilmsEntity = TheBestFilmsEntity(
        id = categoriesFilmsModel.id,
        cover = categoriesFilmsModel.cover
    )

}
