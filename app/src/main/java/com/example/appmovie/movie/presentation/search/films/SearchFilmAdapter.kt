package com.example.appmovie.movie.presentation.search.films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemFilmBinding
import com.example.appmovie.movie.presentation.search.SearchUiState
import javax.inject.Inject

class SearchFilmAdapter @Inject constructor(
    private val glide: RequestManager,
) : ListAdapter<SearchUiState.Success.FilmSearch, SearchFilmHolder>(SearchFilmDiffCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchFilmHolder = SearchFilmHolder(
        binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide
    )

    override fun onBindViewHolder(
        holder: SearchFilmHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))
    }
}
