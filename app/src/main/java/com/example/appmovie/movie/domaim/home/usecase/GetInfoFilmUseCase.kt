package com.example.appmovie.movie.domaim.home.usecase

import com.example.appmovie.movie.data.remote.model.FilmItem
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.entity.InfoFilmEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetInfoFilmUseCase @Inject constructor(private val filmRepository: FilmRepository) {
    operator fun invoke(id: Int): Flow<List<InfoFilmEntity>> =
        filmRepository.getInformationOfFilm(id = id).map {
            it.convertToEntity()
        }

    private fun FilmItem.convertToEntity(): List<InfoFilmEntity> =
        this.convertToEntity()
            .mapIndexed { index, item ->
                InfoFilmEntity(
                    id = item.id,
                    cover = item.cover,
                    promoCover = item.promoCover,
                    year = item.year,
                    rating = item.rating,
                    time = item.time,
                    genre = item.genre,
                )
            }
}
