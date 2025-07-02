package com.example.appmovie.movie.presentation.home.componentsforcategories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemCategoriesCoverBinding
import com.example.appmovie.movie.presentation.home.HomeUiState

class CategoriesFilmsAdapter(
    private val glide: RequestManager,
    private val onItemClick: (Int) -> Unit
) : ListAdapter<HomeUiState.FilmItemState, CategoriesFilmsHolder>(CategoriesDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesFilmsHolder = CategoriesFilmsHolder(
        binding = ItemCategoriesCoverBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide,
        onItemClick
    )

    override fun onBindViewHolder(
        holder: CategoriesFilmsHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))
    }
}
