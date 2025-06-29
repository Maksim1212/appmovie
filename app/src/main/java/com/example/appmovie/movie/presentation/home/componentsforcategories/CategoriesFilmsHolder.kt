package com.example.appmovie.movie.presentation.home.componentsforcategories

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemCategoriesCoverBinding
import com.example.appmovie.movie.presentation.home.HomeFragment
import com.example.appmovie.movie.presentation.home.HomeUiState

class CategoriesFilmsHolder(
    val binding: ItemCategoriesCoverBinding,
    private val glide: RequestManager,
    private val onItemClick: (HomeUiState.FilmItemState) -> Unit

) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(filmItemState: HomeUiState.FilmItemState) {
        with(binding) {
            glide
                .load(filmItemState.image.toUri())
                .into(coverCategoriesFilm)

            itemView.setOnClickListener {
                onItemClick(filmItemState)
            }

        }
    }
}
