package com.example.appmovie.movie.domain.favorite.usecase

import com.example.appmovie.movie.data.db.FavoriteFilmData
import com.example.appmovie.movie.data.db.FilmFavoriteRepository
import com.example.appmovie.movie.domain.favorite.entity.FavoriteFilmDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveFavoriteFilmUseCase @Inject constructor(
    private val filmFavoriteRepository: FilmFavoriteRepository
) {
    operator fun invoke(favoriteFilmDomain: FavoriteFilmDomain): Flow<Unit> {
        return filmFavoriteRepository.saveFilmFavorite(favoriteFilmData = favoriteFilmDomain.convertToModel())
    }

    fun FavoriteFilmDomain.convertToModel(): FavoriteFilmData =
        FavoriteFilmData(
            id = this.id,
            nameRu = this.nameRu,
            cover = this.cover,
            filmLength = this.filmLength,
            genre = this.genre,
            rating = this.rating,
            year = this.year,
        )
}
