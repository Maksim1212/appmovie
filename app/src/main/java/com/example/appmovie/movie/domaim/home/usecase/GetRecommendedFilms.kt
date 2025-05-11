package com.example.appmovie.movie.domaim.home.usecase

import com.example.appmovie.movie.data.RecommendedFilmsModel
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.entity.RecommendedFilmsEntity

class GetRecommendedFilms(private val filmRepository: FilmRepository) {

    operator fun invoke(): List<RecommendedFilmsEntity> {
        val models = filmRepository.getRecommendedFilms()
        return models.map {
            convertRecommendedFilmModelToEntity(it)
        }
    }

    private fun convertRecommendedFilmModelToEntity(
        recommendedFilmsModel: RecommendedFilmsModel
    ): RecommendedFilmsEntity = RecommendedFilmsEntity(
        id = recommendedFilmsModel.id,
        cover = recommendedFilmsModel.cover
    )

}
