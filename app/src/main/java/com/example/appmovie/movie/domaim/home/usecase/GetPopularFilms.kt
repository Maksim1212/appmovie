package com.example.appmovie.movie.domaim.home.usecase

import com.example.appmovie.movie.data.CategoriesFilmsModel
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.entity.NewFilmsEntity
import com.example.appmovie.movie.domaim.home.entity.PopularFilmsEntity

class GetPopularFilms(private val filmRepository: FilmRepository) {

    operator fun invoke(): List<PopularFilmsEntity> {
        val models = filmRepository.getPopularFilms()
        return models.map {
            convertCategoriesFilmsModelToEntity(it)
        }

    }

    private fun convertCategoriesFilmsModelToEntity(
        categoriesFilmsModel: CategoriesFilmsModel
    ): PopularFilmsEntity = PopularFilmsEntity(
        id = categoriesFilmsModel.id,
        cover = categoriesFilmsModel.cover
    )

}
