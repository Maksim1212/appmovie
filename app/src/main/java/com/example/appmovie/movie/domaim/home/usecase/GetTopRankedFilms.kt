package com.example.appmovie.movie.domaim.home.usecase

import com.example.appmovie.movie.data.RankedFilmModel
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.entity.RankedFilmEntity

class GetTopRankedFilms(private val filmRepository: FilmRepository) {

    operator fun invoke(): List<RankedFilmEntity> {
        val models = filmRepository.getTopRankedFilms()
        return models.map {
            convertRankedFilmModelToEntity(it)
        }
    }

    private fun convertRankedFilmModelToEntity(
        rankedFilmModel: RankedFilmModel
    ): RankedFilmEntity = RankedFilmEntity(
        id = rankedFilmModel.id,
        cover = rankedFilmModel.cover,
        rank = rankedFilmModel.rank
    )
}
