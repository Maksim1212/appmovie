package com.example.appmovie.movie.presentation.home.componentsforcategories

import androidx.recyclerview.widget.DiffUtil
import com.example.appmovie.movie.presentation.home.HomeUiState

class CategoriesDiffCallback : DiffUtil.ItemCallback<HomeUiState.FilmItemState>() {

    override fun areItemsTheSame(oldItem: HomeUiState.FilmItemState, newItem: HomeUiState.FilmItemState): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: HomeUiState.FilmItemState, newItem: HomeUiState.FilmItemState): Boolean {
        return oldItem == newItem
    }
}
