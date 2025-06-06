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
import com.example.appmovie.movie.data.remote.RetrofitContainer
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.usecase.GetFilmByGenreUseCase
import com.example.appmovie.movie.domaim.home.usecase.GetTopRankedFilmsUseCase
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val retrofitContainer = RetrofitContainer
    private var filmRepository = FilmRepository(
        kinopoiskApi = retrofitContainer.kinopoiskApi
    )
    private val homeViewModel = HomeViewModel(
        getFilmByGenreUseCase = GetFilmByGenreUseCase(filmRepository),
        getTopRankedFilmsUseCase = GetTopRankedFilmsUseCase(filmRepository)
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

        binding.erorToTryButton.setOnClickListener {

        }

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

        tabLayout.addTab(tabLayout.newTab().setText(R.string.categories_tl_dramma))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.categories_tl_fantastic))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.categories_tl_the_comedy))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.categories_tl_horror))

        val spanCount = 3
        val layoutManager = GridLayoutManager(requireContext(), spanCount)
        recyclerView.layoutManager = layoutManager

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                homeViewModel.loadFilmsCategory(tab.position)
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
                    when {
                        !homeState.hasError -> {
                            rankedFilmsAdapter?.submitList(homeState.rankedFilms)
                            categoriesFilmsAdapter?.submitList(homeState.films)
                            binding.rvRankedFilms.visibility = View.VISIBLE
                            binding.rvCategories.visibility = View.VISIBLE
                            binding.tabLayoutHomeFr.visibility = View.VISIBLE
                            binding.erorImageView.visibility = View.GONE
                            binding.erorTextView1.visibility = View.GONE
                            binding.erorTextView2.visibility = View.GONE
                            binding.erorToTryButton.visibility = View.GONE
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        homeState.hasError -> {
                            binding.erorImageView.visibility = View.VISIBLE
                            binding.erorToTryButton.visibility = View.VISIBLE
                            binding.erorTextView1.visibility = View.VISIBLE
                            binding.erorTextView2.visibility = View.VISIBLE
                            binding.rvRankedFilms.visibility = View.GONE
                            binding.rvCategories.visibility = View.GONE
                            binding.tabLayoutHomeFr.visibility = View.GONE
                            binding.progressBar.visibility = View.GONE

                            binding.erorToTryButton.setOnClickListener {
                                homeViewModel.loadInitialData()
                            }
                        }
                    }
                }

            }
        }
    }

    private fun addDecorators() {
        val topOffset = resources.getDimensionPixelSize(R.dimen.top_offset)
        val rightOffset = resources.getDimensionPixelSize(R.dimen.right_offset)
        val bottomOffset = resources.getDimensionPixelSize(R.dimen.bottom_offset)

        val itemDecorator = CategoriesFilmsItemDecoration(topOffset, rightOffset, bottomOffset)
        binding.rvCategories.addItemDecoration(itemDecorator)
    }
}
