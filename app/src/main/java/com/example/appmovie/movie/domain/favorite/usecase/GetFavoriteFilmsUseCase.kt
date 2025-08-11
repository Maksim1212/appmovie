package com.example.appmovie.movie.domain.favorite.usecase

import com.example.appmovie.movie.data.db.FavoriteFilmEntity
import com.example.appmovie.movie.data.db.FilmFavoriteRepository
import com.example.appmovie.movie.domain.favorite.entity.FilmEntityFavorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteFilmsUseCase @Inject constructor(
    private val filmFavoriteRepository: FilmFavoriteRepository
) {
    operator fun invoke(): Flow<List<FilmEntityFavorite>> {
        return filmFavoriteRepository.getFilmsFavorite().map {
            it.
        }

        fun List<FavoriteFilmEntity>.convertToEntity(): List<FilmEntityFavorite> =
            this.mapNotNull { item ->
                item?.let {
                    FilmEntityFavorite(
                        id = item.id,
                        nameRu = item.nameRu,
                        cover = item.cover,
                        filmLength = item.filmLength,
                        genre = item.genre,
                        rating = item.rating,
                        year = item.year,
                    )
                }
            }
    }
