package com.example.appmovie.movie.domain.favorite.usecase

import com.example.appmovie.movie.data.db.FilmFavoriteRepository
import com.example.appmovie.movie.domain.favorite.entity.FilmEntityFavorite
import javax.inject.Inject

class DeleteFavoriteFilmUseCase @Inject constructor(
    private val filmFavoriteRepository: FilmFavoriteRepository) {
    operator fun invoke(filmEntityFavorite: FilmEntityFavorite) {
        filmFavoriteRepository.deleteFilmFavorite(filmEntityFavorite)
    }
}
