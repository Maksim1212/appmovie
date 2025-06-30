package com.example.appmovie.movie.presentation.home

data class HomeUiState(
    val headerText: String = "",
    val rankedFilms: List<RankedFilmItemState> = emptyList(),
    val films: List<FilmItemState> = emptyList(),
    val isError: Boolean = false,
    val isLoading: Boolean = false

) {

    data class RankedFilmItemState(
        val id: Int,
        val image: String = "",
        val rank: String = ""
    )

    data class FilmItemState(
        val id: Int,
        val image: String = "",
    )
}
