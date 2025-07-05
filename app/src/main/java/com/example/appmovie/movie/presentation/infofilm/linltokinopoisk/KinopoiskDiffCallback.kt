package com.example.appmovie.movie.presentation.infofilm.linltokinopoisk

import androidx.recyclerview.widget.DiffUtil
import com.example.appmovie.movie.presentation.infofilm.InfoFilmUiState

class KinopoiskDiffCallback : DiffUtil.ItemCallback<InfoFilmUiState.Success>() {

    override fun areItemsTheSame(
        oldItem: InfoFilmUiState.Success,
        newItem: InfoFilmUiState.Success,
    ): Boolean {
        return oldItem.webUrl == newItem.webUrl
    }

    override fun areContentsTheSame(
        oldItem: InfoFilmUiState.Success,
        newItem: InfoFilmUiState.Success,
    ): Boolean {
        return oldItem == newItem
    }
}
