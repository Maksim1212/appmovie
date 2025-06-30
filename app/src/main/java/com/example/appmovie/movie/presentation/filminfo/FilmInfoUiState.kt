package com.example.appmovie.movie.presentation.filminfo

sealed class FilmInfoUiState {
    data class HomeUiState(
        val headerText: String = "",
        val actorsFilm: List<ActorsFilm> = emptyList(),
        val filmsInfo: List<FilmInfo> = emptyList(),
        val isError: Boolean = false,
        val isLoading: Boolean = false
    ) : FilmInfoUiState()

    data class ActorsFilm(
        val id: Int,
    ) : FilmInfoUiState()

    data class FilmInfo(
        val id: Int,
    ) : FilmInfoUiState()
}
