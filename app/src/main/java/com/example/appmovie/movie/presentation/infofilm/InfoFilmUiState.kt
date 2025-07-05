package com.example.appmovie.movie.presentation.infofilm

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
        val shortDescription: String = "",
    ) : InfoFilmUiState {

        data class Actors(
            val nameActors: String = "",
            val coverActors: String = "",
        )
    }

    data object Loading : InfoFilmUiState

    data object Error : InfoFilmUiState
}
