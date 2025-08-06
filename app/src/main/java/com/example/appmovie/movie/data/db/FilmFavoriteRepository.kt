package com.example.appmovie.movie.data.db

import com.example.appmovie.movie.domain.favorite.entity.FilmEntityFavorite
import javax.inject.Inject

class FilmFavoriteRepository @Inject constructor(
    private val favoriteFilmDao: FavoriteFilmDao
) {
    fun saveFilmFavorite(favoriteFilmEntity: FavoriteFilmEntity) {
        favoriteFilmDao.saveFavoriteFilm(favoriteFilmEntity)
    }

    fun getFilmsFavorite(filmEntityFavorite: FilmEntityFavorite): List<FavoriteFilmEntity> {
        return favoriteFilmDao.getAllFavoriteFilms()
    }

    fun deleteFilmFavorite(favoriteFilmEntity: FilmEntityFavorite) {
        favoriteFilmDao.deleteFavoriteFilm(favoriteFilmEntity)
    }

    companion object {
        private const val DATABASE_NAME = "appmovie.db"
    }
}
