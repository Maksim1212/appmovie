package com.example.appmovie.movie.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemCategoriesCoverBinding

class CategoriesFilmsAdapter(
    private val list: List<HomeUiState.FilmItemState>,
    private val glide: RequestManager
) : RecyclerView.Adapter<CategoriesFilmsHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesFilmsHolder = CategoriesFilmsHolder(
        binding = ItemCategoriesCoverBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide
    )

    override fun onBindViewHolder(
        holder: CategoriesFilmsHolder,
        position: Int
    ) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
