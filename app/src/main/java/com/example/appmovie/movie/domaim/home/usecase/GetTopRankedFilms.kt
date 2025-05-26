package com.example.appmovie.movie.domaim.home.usecase

import com.example.appmovie.movie.data.RankedFilmModel
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.entity.RankedFilmEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTopRankedFilms(private val filmRepository: FilmRepository) {

    operator fun invoke(): Flow<RankedFilmEntity> = flow {
        filmRepository.getTopRankedFilms().forEach { model ->
            emit(convertRankedFilmModelToEntity(model))
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
