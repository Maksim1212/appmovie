package com.example.appmovie.movie.presentation.search

interface SearchUiState {

    data class Success(
        val headerText: String = "",
        val searchFilms: List<FilmSearch> = emptyList(),
        val headerSearch: String = "",

    ) : SearchUiState {
        data class FilmSearch(
            val id: Int,
            val cover: String = "",
            val rating: String = "",
            val year: String = "",
            val filmLength: String = "",
            val genre: String = "",
            val nameRu: String = "",
        )
    }

    data object Loading : SearchUiState

    data object Error : SearchUiState
}
