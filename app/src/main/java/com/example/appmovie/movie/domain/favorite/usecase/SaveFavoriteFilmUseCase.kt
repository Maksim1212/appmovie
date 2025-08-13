package com.example.appmovie.movie.domain.favorite.usecase

import com.example.appmovie.movie.data.db.FavoriteFilmData
import com.example.appmovie.movie.data.db.FilmFavoriteRepository
import com.example.appmovie.movie.domain.favorite.entity.FavoriteFilmDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SaveFavoriteFilmUseCase @Inject constructor(
    private val filmFavoriteRepository: FilmFavoriteRepository
) {
    operator fun invoke(favoriteFilmDomain: FavoriteFilmDomain): Flow<Unit> {
        return filmFavoriteRepository.saveFilmFavorite(favoriteFilmDomain).map {
            it.
        }
    }

    fun List<FavoriteFilmData>.convertToEntity(): List<FavoriteFilmDomain> =
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
