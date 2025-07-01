package com.example.appmovie.movie.presentation.filminfo

sealed interface InfoUiState {
    data class Success(
        val id: Int,
        val headerText: String = "",
        val promoCover: String = "",
        val cover: String = "",
        val rating: String = "",
        val year: String = "",
        val time: String = "",
        val genre: String = "",
        val actors: List<Actors> = emptyList(),
    ) : InfoUiState {

        data class Actors(
            val nameActors: String = "",
            val coverActors: String = "",
        )
    }

    // data object Loading() : InfoUiState
    //
    // data object Error() : InfoUiState
}
