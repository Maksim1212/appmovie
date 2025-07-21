package com.example.appmovie.movie.domain.infofilm.usecase

import com.example.appmovie.movie.data.remote.model.ItemActors
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domain.infofilm.entity.ActorsFilmEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetActorsFilmsUseCase @Inject constructor(private val filmRepository: FilmRepository) {

    operator fun invoke(id: Int): Flow<List<ActorsFilmEntity>> =
        filmRepository.getActorsFilm(id = id).map {
            it.convertToEntity()
        }

    private fun List<ItemActors?>.convertToEntity(): List<ActorsFilmEntity> =
        this.mapNotNull { item ->
            item?.let {
                ActorsFilmEntity(
                    id = item.staffId,
                    nameActors = item.nameRu,
                    coverActors = item.posterUrl,
                )
            }
        }
}
