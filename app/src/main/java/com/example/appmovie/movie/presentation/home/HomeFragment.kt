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
import com.example.appmovie.movie.domaim.home.usecase.GetNewFilms
import com.example.appmovie.movie.domaim.home.usecase.GetPopularFilms
import com.example.appmovie.movie.domaim.home.usecase.GetRecommendedFilms
import com.example.appmovie.movie.domaim.home.usecase.GetTheBestFilms
import com.example.appmovie.movie.domaim.home.usecase.GetTopRankedFilms
import com.google.android.material.tabs.TabLayout

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

        recyclerViewForTheMovieRankedFilms()

        val tabLayout = binding.tabLayoutHomeFr
        val recyclerView = binding.rvCategories

        tabLayout.addTab(tabLayout.newTab().setText(R.string.categories_tl_new))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.categories_tl_popular))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.categories_tl_recommended))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.categories_tl_the_best))

        val popularFilms = GetPopularFilms(filmRepository).invoke().map {
            convertCategoriesFilmEntityToFilmItemState(it)
        }

        val adapter = CategoriesFilmsAdapter(
            popularFilms,
            glide = Glide.with(this@HomeFragment)
        )

        val spanCount = 3
        val layoutManager = GridLayoutManager(requireContext(), spanCount)
        recyclerView.layoutManager = layoutManager

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val data = when (tab.position) {
                    0 -> GetPopularFilms(filmRepository).invoke()
                    1 -> GetNewFilms(filmRepository).invoke()
                    2 -> GetTheBestFilms(filmRepository).invoke()
                    3 -> GetRecommendedFilms(filmRepository).invoke()
                    else -> GetPopularFilms(filmRepository).invoke()
                }.map { convertCategoriesFilmEntityToFilmItemState(it) }
                adapter.submitList(data)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.rvCategories.adapter = adapter

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

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager


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
