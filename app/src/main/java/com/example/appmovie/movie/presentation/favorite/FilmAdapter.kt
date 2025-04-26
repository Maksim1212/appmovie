package com.example.appmovie.movie.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.appmovie.Film
import com.example.appmovie.FilmHolder
import com.example.appmovie.MyDiffCallback
import com.example.appmovie.databinding.ItemFilmBinding

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
