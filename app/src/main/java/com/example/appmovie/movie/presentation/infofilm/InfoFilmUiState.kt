package com.example.appmovie.movie.presentation.infofilm

import com.example.appmovie.movie.domain.favorite.entity.FavoriteFilmDomain

sealed interface InfoFilmUiState {
    data class Success(
        val id: Int,
        val headerText: String = "",
        val promoCover: String = "",
        val cover: String = "",
        val rating: String = "",
        val year: String = "",
        val filmLength: String = "",
        val genre: String = "",
        val actors: List<Actors> = emptyList(),
        val description: String = "",
        val webUrl: String = "",
        val selectedTab: Int = 0,
        val nameRu: String = "",
        val isFilmFavorite: Boolean = false
    ) : InfoFilmUiState {

        data class Actors(
            val nameActors: String = "",
            val coverActors: String = "",
        )
    }

    data object Loading : InfoFilmUiState

    data object Error : InfoFilmUiState

}

fun InfoFilmUiState.Success.convertToDomain(): FavoriteFilmDomain =
    FavoriteFilmDomain(
        id = this.id,
        nameRu = this.nameRu,
        cover = this.cover,
        filmLength = this.filmLength,
        genre = this.genre,
        rating = this.rating,
        year = this.year,
    )
