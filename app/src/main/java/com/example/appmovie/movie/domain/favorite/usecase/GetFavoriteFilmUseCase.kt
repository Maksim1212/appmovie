package com.example.appmovie.movie.domain.favorite.usecase

import com.example.appmovie.movie.data.db.FilmFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteFilmUseCase @Inject constructor(
    private val filmFavoriteRepository: FilmFavoriteRepository
) {
    operator fun invoke(id: Int): Flow<Int?> =
        filmFavoriteRepository.getFilmFavorite(id)
}
