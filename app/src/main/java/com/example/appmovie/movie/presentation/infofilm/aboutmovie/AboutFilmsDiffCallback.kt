package com.example.appmovie.movie.presentation.infofilm.aboutmovie

import androidx.recyclerview.widget.DiffUtil
import com.example.appmovie.movie.presentation.infofilm.InfoFilmUiState

class AboutFilmsDiffCallback : DiffUtil.ItemCallback<InfoFilmUiState.Success>() {

    override fun areItemsTheSame(
        oldItem: InfoFilmUiState.Success,
        newItem: InfoFilmUiState.Success,
    ): Boolean {
        return oldItem.shortDescription == newItem.shortDescription
    }

    override fun areContentsTheSame(
        oldItem: InfoFilmUiState.Success,
        newItem: InfoFilmUiState.Success,
    ): Boolean {
        return oldItem == newItem
    }
}
