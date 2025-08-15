package com.example.appmovie.movie.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.appmovie.R
import com.example.appmovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.searchFragment, R.id.favoriteFragment -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                }

                else -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
            }
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.searchFragment -> {
                    navController.navigate(R.id.searchFragment)
                    true
                }

                R.id.favoriteFragment -> {
                    navController.navigate(R.id.favoriteFragment)
                    true
                }

                else -> false
            }
        }
    }
}

