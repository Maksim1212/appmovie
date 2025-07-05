package com.example.appmovie.movie.domaim.infofilm.usecase

import com.example.appmovie.movie.data.remote.model.FilmItem
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.infofilm.entity.InfoFilmEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetInfoFilmUseCase @Inject constructor(private val filmRepository: FilmRepository) {
    operator fun invoke(id: Int): Flow<InfoFilmEntity> =
        filmRepository.getInformationOfFilm(id = id).map {
            it.convertToEntity()
        }

    private fun FilmItem.convertToEntity(): InfoFilmEntity {
        return InfoFilmEntity(
            id = kinopoiskId,
            cover = coverUrl ?: "",
            promoCover = posterUrl,
            year = year?.toString() ?: "",
            rating = ratingKinopoisk?.toString() ?: "",
            filmLength = filmLength?.toString() ?: "",
            genre = genres?.joinToString { it.genre } ?: "",
            shortDescription = shortDescription,
            webUrl = webUrl,
        )
    }
}
