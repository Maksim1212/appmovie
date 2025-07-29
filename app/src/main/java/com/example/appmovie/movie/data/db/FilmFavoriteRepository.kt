package com.example.appmovie.movie.data.db

import javax.inject.Inject

class FilmFavoriteRepository @Inject constructor(
    private val favoriteFilmDao: FavoriteFilmDao
) {
    fun saveFilmFavorite(favoriteFilmEntity: FavoriteFilmEntity) {
        favoriteFilmDao.saveFavoriteFilm(favoriteFilmEntity)
    }

    fun getFilmsFavorite(): List<FavoriteFilmEntity> {
        return favoriteFilmDao.getAllFavoriteFilms()
    }

    fun deleteFilmFavorite(favoriteFilmEntity: FavoriteFilmEntity) {
        favoriteFilmDao.deleteFavoriteFilm(favoriteFilmEntity)
    }

    companion object {
        private const val DATABASE_NAME = "appmovie.db"
    }
}
