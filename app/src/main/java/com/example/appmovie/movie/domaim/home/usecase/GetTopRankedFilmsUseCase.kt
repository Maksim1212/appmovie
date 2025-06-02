package com.example.appmovie.movie.domaim.home.usecase

import com.example.appmovie.movie.data.remote.model.CollectionsResponse
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.entity.RankedFilmEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTopRankedFilmsUseCase(private val filmRepository: FilmRepository) {

    operator fun invoke(): Flow<List<RankedFilmEntity>> =
        filmRepository.getTopRankedFilms().map {
            it.convertToEntity()
        }

    private fun CollectionsResponse.convertToEntity(): List<RankedFilmEntity> =
        this.items
            .filterNotNull()
            .mapIndexed { index, item ->
                RankedFilmEntity(
                    id = item.kinopoiskId,
                    cover = item.posterUrlPreview,
                    rank = index + 1
                )
            }
}
