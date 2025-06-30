package com.example.appmovie.movie.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide
import com.example.appmovie.R
import com.example.appmovie.movie.App
import com.example.appmovie.movie.presentation.home.componentsforcategories.CategoriesFilmsAdapter
import com.example.appmovie.movie.presentation.home.componentsforcategories.CategoriesFilmsItemDecoration
import com.example.appmovie.movie.presentation.home.rankedadapter.RankedHorizontalSpacingItemDecoration
import com.example.appmovie.movie.presentation.home.rankedadapter.RankedFilmsAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var rankedFilmsAdapter: RankedFilmsAdapter? = null
    private var categoriesFilmsAdapter: CategoriesFilmsAdapter? = null

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

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
            homeViewModel.loadInitialData()
        }
    }

    private fun openInformationFragment(id: Int) {
        val navController = this.findNavController()
        val bundle = Bundle().apply {
            putInt("id", id)
        }
        navController.navigate(R.id.informationFilmFragment, bundle)
    }

    private fun recyclerViewForTheMovieRankedFilms() {

        val recyclerView: RecyclerView = binding.rvRankedFilms
        rankedFilmsAdapter = RankedFilmsAdapter(
            glide = Glide.with(this@HomeFragment)
        ) { id ->
            openInformationFragment(id = id)
        }

        recyclerView.adapter = rankedFilmsAdapter

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.size_medium)
        binding.rvRankedFilms.addItemDecoration(
            RankedHorizontalSpacingItemDecoration(
                spacingInPixels
            )
        )

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

    }

    private fun recyclerViewForCategoriesFilms() {
        val tabLayout = binding.tabLayoutHomeFr
        val recyclerView = binding.rvCategories

        categoriesFilmsAdapter = CategoriesFilmsAdapter(
            glide = Glide.with(this@HomeFragment)
        ) { id ->
            openInformationFragment(id = id)
        }

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
                    if (homeState.isLoading) {
                        showLoading()
                        hideError()
                        hideContent()
                    }
                    if (homeState.isError) {
                        showError()
                        hideLoading()
                        hideContent()
                    }
                    if (!homeState.isLoading
                        && homeState.rankedFilms.isNotEmpty()
                        && homeState.films.isNotEmpty()
                        && !homeState.isError
                    ) {
                        rankedFilmsAdapter?.submitList(homeState.rankedFilms)
                        categoriesFilmsAdapter?.submitList(homeState.films)

                        showContent()
                        hideLoading()
                        hideError()
                    }
                }
            }
        }
    }

    private fun addDecorators() {
        val topOffset = resources.getDimensionPixelSize(R.dimen.top_offset)
        val rightOffset = resources.getDimensionPixelSize(R.dimen.right_offset)
        val bottomOffset = resources.getDimensionPixelSize(R.dimen.bottom_offset)

        val itemDecorator =
            CategoriesFilmsItemDecoration(topOffset, rightOffset, bottomOffset)
        binding.rvCategories.addItemDecoration(itemDecorator)
    }

    private fun showError() {
        with(binding) {
            erorImageView.show()
            erorToTryButton.show()
            erorTextView1.show()
            erorTextView2.show()
        }
    }

    private fun hideError() {
        with(binding) {
            erorImageView.hide()
            erorTextView1.hide()
            erorTextView2.hide()
            erorToTryButton.hide()
        }
    }

    private fun showContent() {
        with(binding) {
            rvRankedFilms.show()
            rvCategories.show()
            tabLayoutHomeFr.show()
        }
    }

    private fun hideContent() {
        with(binding) {
            rvRankedFilms.hide()
            rvCategories.hide()
            tabLayoutHomeFr.hide()
        }
    }

    private fun showLoading() {
        with(binding) {
            progressBar.show()
        }
    }

    private fun hideLoading() {
        with(binding) {
            progressBar.hide()
        }
    }

    fun View.show(): View {
        isVisible = true
        return this
    }

    fun View.hide(): View {
        isVisible = false
        return this
    }
}
