package com.example.appmovie.movie.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmovie.databinding.FragmentCategoriesBinding
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.entity.CategoriesFilmEntity
import com.example.appmovie.movie.domaim.home.usecase.GetPopularFilms

class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private var filmRepository = FilmRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topPopularFilms = GetPopularFilms(filmRepository).invoke().map {
            convertCategoriesFilmsEntityToFilmItemState(it)
        }
    }

    private fun convertCategoriesFilmsEntityToFilmItemState(
        categoriesFilmEntity: CategoriesFilmEntity
    ): HomeUiState.FilmItemState = HomeUiState.FilmItemState(
        image = categoriesFilmEntity.cover

        )
}
