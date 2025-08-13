package com.example.appmovie.movie.domain.favorite.usecase

import com.example.appmovie.movie.data.db.FavoriteFilmData
import com.example.appmovie.movie.data.db.FilmFavoriteRepository
import com.example.appmovie.movie.domain.favorite.entity.FavoriteFilmDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteFilmsUseCase @Inject constructor(
    private val filmFavoriteRepository: FilmFavoriteRepository
) {
    operator fun invoke(): Flow<List<FavoriteFilmDomain>> {
        return filmFavoriteRepository.getFilmsFavorite().map {
            it.convertToEntity()
        }
    }

    private fun List<FavoriteFilmData>.convertToEntity(): List<FavoriteFilmDomain> =
        this.mapNotNull { item ->
            item?.let {
                FavoriteFilmDomain(
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
