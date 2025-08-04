package com.example.appmovie.movie.domain.favorite.usecase

import com.example.appmovie.movie.data.db.FavoriteFilmEntity
import com.example.appmovie.movie.data.db.FilmFavoriteRepository
import javax.inject.Inject

class SaveFavoriteFilmUseCase @Inject constructor(private val filmFavoriteRepository: FilmFavoriteRepository) {
    operator fun invoke(favoriteFilmEntity: FavoriteFilmEntity) {
        filmFavoriteRepository.saveFilmFavorite(favoriteFilmEntity)
    }
}
