package com.example.appmovie.movie.presentation.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(categoriesFragment: HomeFragment) : FragmentStateAdapter(categoriesFragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CategoriesFragment()
            1 -> CategoriesFragment()
            2 -> CategoriesFragment()
            3 -> CategoriesFragment()
            else -> CategoriesFragment()
        } as Fragment
    }
}
