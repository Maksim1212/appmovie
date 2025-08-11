package com.example.appmovie.movie.domain.favorite.usecase

import com.example.appmovie.movie.data.db.FilmFavoriteRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteFilmUseCase(
    private val filmFavoriteRepository: FilmFavoriteRepository
) {
    operator fun invoke(id: Int): Flow<Int?> {
        return filmFavoriteRepository.getFilmFavorite(id)
    }
}
