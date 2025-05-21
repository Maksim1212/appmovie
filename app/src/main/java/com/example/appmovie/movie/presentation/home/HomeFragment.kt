package com.example.appmovie.movie.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide
import com.example.appmovie.R
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.entity.CategoriesFilmEntity
import com.example.appmovie.movie.domaim.home.usecase.GetNewFilms
import com.example.appmovie.movie.domaim.home.usecase.GetPopularFilms
import com.example.appmovie.movie.domaim.home.usecase.GetRecommendedFilms
import com.example.appmovie.movie.domaim.home.usecase.GetTheBestFilms
import com.example.appmovie.movie.domaim.home.usecase.GetTopRankedFilms
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var filmRepository = FilmRepository()
    private var homeViewModel = HomeViewModel(
        getPopularFilmsUseCase = GetPopularFilms(filmRepository),
        getNewFilmsUseCase = GetNewFilms(filmRepository),
        getTheBestFilmsUseCase = GetTheBestFilms(filmRepository),
        getRecommendedFilmsUseCase = GetRecommendedFilms(filmRepository),
        getTopRankedFilmsUseCase = GetTopRankedFilms(filmRepository)
    )
    private var rankedFilmsAdapter: RankedFilmsAdapter? = null
    private var categoriesFilmsAdapter: CategoriesFilmsAdapter? = null

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

        recyclerViewForCategoriesFilms()

        observeUiState()

    }

    private fun recyclerViewForTheMovieRankedFilms() {

        val recyclerView: RecyclerView = binding.rvRankedFilms
        rankedFilmsAdapter = RankedFilmsAdapter(
            glide = Glide.with(this@HomeFragment)
        )

        recyclerView.adapter = rankedFilmsAdapter

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.size_medium)
        binding.rvRankedFilms.addItemDecoration(HorizontalSpacingItemDecoration(spacingInPixels))

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

    }

    private fun recyclerViewForCategoriesFilms() {
        val tabLayout = binding.tabLayoutHomeFr
        val recyclerView = binding.rvCategories

        categoriesFilmsAdapter = CategoriesFilmsAdapter(
            glide = Glide.with(this@HomeFragment)
        )

        recyclerView.adapter = categoriesFilmsAdapter

        tabLayout.addTab(tabLayout.newTab().setText(R.string.categories_tl_popular))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.categories_tl_new))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.categories_tl_the_best))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.categories_tl_recommended))

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
                categoriesFilmsAdapter?.submitList(data)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        addDecorators()

    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.uiState.collect { homeState ->
                    rankedFilmsAdapter?.submitList(homeState.rankedFilms)
                    categoriesFilmsAdapter?.submitList(homeState.films)
                }
            }
        }
    }

    private fun convertCategoriesFilmEntityToFilmItemState(
        categoriesFilmEntity: CategoriesFilmEntity
    ): HomeUiState.FilmItemState = HomeUiState.FilmItemState(
        image = categoriesFilmEntity.cover,
    )

    private fun addDecorators() {
        val topOffset = resources.getDimensionPixelSize(R.dimen.top_offset)
        val rightOffset = resources.getDimensionPixelSize(R.dimen.right_offset)
        val bottomOffset = resources.getDimensionPixelSize(R.dimen.bottom_offset)

        val itemDecorator = CategoriesFilmsItemDecoration(topOffset, rightOffset, bottomOffset)
        binding.rvCategories.addItemDecoration(itemDecorator)
    }
}
