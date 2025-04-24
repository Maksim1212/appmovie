package com.example.appmovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.appmovie.databinding.ActivityMainBinding
import com.example.appmovie.movie.presentation.favorite.FavoriteFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.run {
            setContentView(root)
            bottomNavigation.setupWithNavController(navController)
            bottomNavigation.setOnItemSelectedListener { item ->
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
}
