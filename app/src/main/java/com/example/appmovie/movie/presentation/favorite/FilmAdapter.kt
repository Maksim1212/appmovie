package com.example.appmovie.movie.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.Film
import com.example.appmovie.FilmItem
import com.example.appmovie.databinding.ItemFilmBinding

class FilmAdapter(
    private val list: List<Film>
): RecyclerView.Adapter<FilmItem>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmItem = FilmItem(
        ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: FilmItem,
        position: Int
    ) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}
