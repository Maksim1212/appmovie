package com.example.appmovie.movie.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemFilmBinding
import com.example.appmovie.movie.common.Film

class FilmAdapter(
    private val list: List<Film>,
    private val action: (Film) -> Unit,
    private val glide: RequestManager

) : ListAdapter<Film, FilmHolder>(MyDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmHolder = FilmHolder(
        binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        action,
        glide

    )

    override fun onBindViewHolder(
        holder: FilmHolder,
        position: Int
    ) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
