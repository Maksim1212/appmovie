package com.example.appmovie.movie.domain.infofilm.usecase

import com.example.appmovie.movie.data.remote.model.CollectionActorsFilm
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

    private fun CollectionActorsFilm.convertToEntity(): List<ActorsFilmEntity> =
        this.convertToEntity()
            .mapIndexed { index, item ->
                ActorsFilmEntity(
                    id = item.id,
                    nameActors = item.nameActors,
                    coverActors = item.coverActors,
                )
            }
}
