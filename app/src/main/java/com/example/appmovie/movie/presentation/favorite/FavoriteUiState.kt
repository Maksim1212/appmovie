package com.example.appmovie.movie.presentation.favorite

sealed interface FavoriteUiState {
    data class Success(
        val headerText: String = "",
        val favoriteFilms: List<FilmFavorite> = emptyList(),
    ) : FavoriteUiState {
        data class FilmFavorite(
            val id: Int,
            val cover: String = "",
            val rating: String = "",
            val year: String = "",
            val filmLength: String = "",
            val genre: String = "",
            val nameRu: String = "",
        )
    }

    data object Loading : FavoriteUiState

    data object Error : FavoriteUiState
}
