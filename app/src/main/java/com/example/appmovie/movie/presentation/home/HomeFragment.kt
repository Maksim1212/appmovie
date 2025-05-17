package com.example.appmovie.movie.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide
import com.example.appmovie.R
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.entity.CategoriesFilmEntity
import com.example.appmovie.movie.domaim.home.entity.RankedFilmEntity
import com.example.appmovie.movie.domaim.home.usecase.GetPopularFilms
import com.example.appmovie.movie.domaim.home.usecase.GetTopRankedFilms

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var filmRepository = FilmRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = binding.tabLayoutHomeFr


        recyclerViewForTheMovieRankedFilms()

        recyclerViewForTheMovieCategoriesFilms()

    }

    private fun recyclerViewForTheMovieRankedFilms() {
        val topRankedFilms = GetTopRankedFilms(filmRepository).invoke().map {
            convertRankedFilmEntityToRankedFilmItemState(it)
        }

        val recyclerView: RecyclerView = binding.rvRankedFilms
        binding.rvRankedFilms.adapter = RankedFilmsAdapter(
            topRankedFilms,
            glide = Glide.with(this@HomeFragment)
        )

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.size_medium)
        binding.rvRankedFilms.addItemDecoration(HorizontalSpacingItemDecoration(spacingInPixels))

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
    }

    private fun recyclerViewForTheMovieCategoriesFilms() {
        val popularFilms = GetPopularFilms(filmRepository).invoke().map {
            convertCategoriesFilmEntityToFilmItemState(it)
        }

        val recyclerView = binding.rvCategories

        val spanCount = 3

        val layoutManager = GridLayoutManager(requireContext(), spanCount)
        recyclerView.layoutManager = layoutManager

        binding.rvCategories.adapter = CategoriesFilmsAdapter(
            popularFilms,
            glide = Glide.with(this@HomeFragment)
        )

    }

    private fun convertRankedFilmEntityToRankedFilmItemState(
        rankedFilmEntity: RankedFilmEntity
    ): HomeUiState.RankedFilmItemState = HomeUiState.RankedFilmItemState(
        image = rankedFilmEntity.cover,
        rank = rankedFilmEntity.rank.toString()
    )

    private fun convertCategoriesFilmEntityToFilmItemState(
        categoriesFilmEntity: CategoriesFilmEntity
    ): HomeUiState.FilmItemState = HomeUiState.FilmItemState(
        image = categoriesFilmEntity.cover,

    )
}
