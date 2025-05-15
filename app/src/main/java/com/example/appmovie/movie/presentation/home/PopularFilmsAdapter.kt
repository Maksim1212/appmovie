package com.example.appmovie.movie.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemTopMainBinding

class PopularFilmsAdapter(
    private val list: List<HomeUiState.RankedFilmItemState>,
    private val glide: RequestManager
) : RecyclerView.Adapter<PopularFilmsHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularFilmsHolder = PopularFilmsHolder(
        binding = ItemTopMainBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide
    )

    override fun onBindViewHolder(
        holder: PopularFilmsHolder,
        position: Int
    ) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
