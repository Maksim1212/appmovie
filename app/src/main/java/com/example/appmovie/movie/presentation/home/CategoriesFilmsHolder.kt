package com.example.appmovie.movie.presentation.home

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemCategoriesCoverBinding
import com.example.appmovie.movie.presentation.home.HomeUiState.FilmItemState

class CategoriesFilmsHolder(
    val binding: ItemCategoriesCoverBinding,
    private val glide: RequestManager

) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(filmItemState: FilmItemState) {
        with(binding) {
            glide
                .load(filmItemState.image.toUri())
                .into(coverCategoriesFilm)
        }
    }
}
