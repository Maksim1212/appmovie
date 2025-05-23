package com.example.appmovie.movie.presentation.home

data class HomeUiState(
    val headerText: String = "",
    val rankedFilms: List<RankedFilmItemState> = emptyList(),
    val films: List<FilmItemState> = emptyList()
) {

    data class RankedFilmItemState(
        val image: String = "",
        val rank: String = ""
    )

    data class FilmItemState(
        val image: String = ""
    )
}
