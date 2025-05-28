package com.example.appmovie.movie.presentation.home


import androidx.recyclerview.widget.DiffUtil
import com.example.appmovie.movie.presentation.home.HomeUiState.FilmItemState

class CategoriesDiffCallback : DiffUtil.ItemCallback<FilmItemState>() {

    override fun areItemsTheSame(oldItem: FilmItemState, newItem: FilmItemState): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FilmItemState, newItem: FilmItemState): Boolean {
        return oldItem == newItem
    }
}
