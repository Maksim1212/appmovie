package com.example.appmovie.movie.domain.search.usecase

import com.example.appmovie.movie.data.remote.model.Film
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domain.search.entity.SearchFilmEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSearchFilmsByTitleUseCase @Inject constructor(
    private val repository: FilmRepository
) {
    operator fun invoke(query: String): Flow<List<SearchFilmEntity>> {
        return repository.searchFilmsByTitle(query)
            .map { list ->
                list.map { it.toEntity() }
            }
    }

    private fun Film.toEntity(): SearchFilmEntity {
        return SearchFilmEntity(
            id = filmId ?: 0,
            cover = posterUrl ?: "",
            rating = rating ?: "",
            year = year ?: "",
            filmLength = filmLength ?: "",
            genre = genres?.firstOrNull()?.genre.orEmpty(),
            nameRu = nameRu ?: ""
        )
    }
}
