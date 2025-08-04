package com.example.appmovie.movie.domain.favorite.usecase

import com.example.appmovie.movie.data.db.FavoriteFilmEntity
import com.example.appmovie.movie.data.db.FilmFavoriteRepository
import javax.inject.Inject

class GetFavoriteFilmsUseCase @Inject constructor(private val filmFavoriteRepository: FilmFavoriteRepository) {
    operator fun invoke(): List<FavoriteFilmEntity> {
        return filmFavoriteRepository.getFilmsFavorite()
    }
}
