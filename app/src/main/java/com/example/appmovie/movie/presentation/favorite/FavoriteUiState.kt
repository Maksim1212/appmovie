package com.example.appmovie.movie.presentation.favorite

sealed interface FavoriteUiState {
    data class Success (
        val id: Int,
        val headerText: String = "",
        val cover: String = "",
        val rating: String = "",
        val year: String = "",
        val filmLength: String = "",
        val genre: String = "",
        val nameRu: String = "",
    ): FavoriteUiState

    data object Loading : FavoriteUiState

    data object Error : FavoriteUiState
}
