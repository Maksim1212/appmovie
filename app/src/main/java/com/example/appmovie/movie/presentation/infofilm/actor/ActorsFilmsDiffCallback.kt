package com.example.appmovie.movie.presentation.infofilm.actor

import androidx.recyclerview.widget.DiffUtil
import com.example.appmovie.movie.presentation.infofilm.InfoFilmUiState

class ActorsFilmsDiffCallback : DiffUtil.ItemCallback<InfoFilmUiState.Success.Actors>() {

    override fun areItemsTheSame(
        oldItem: InfoFilmUiState.Success.Actors,
        newItem: InfoFilmUiState.Success.Actors,
    ): Boolean {
        return oldItem.nameActors == newItem.nameActors
        oldItem.coverActors == newItem.coverActors
    }

    override fun areContentsTheSame(
        oldItem: InfoFilmUiState.Success.Actors,
        newItem: InfoFilmUiState.Success.Actors,
    ): Boolean {
        return oldItem == newItem
    }
}
