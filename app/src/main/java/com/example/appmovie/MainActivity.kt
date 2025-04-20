package com.example.appmovie

import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.appmovie.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {


    /*(1 вариант)
    val navHostFragment = supportFragmentManager
        .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController

    binding.bottomnavigation.setupWithNavController(navController)*/


    /*(2 вариант)
    val navController = findNavController(R.id.nav_host_fragment)

    binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
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
    }*/

    /* (3 вариант)
    val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController

    binding.bottomNavigation.setupWithNavController(navController)

    binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
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
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    /*(4вариант)
    val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomnavigation)
    val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController

    val appBarConfiguration = AppBarConfiguration(
        setOf(
            R.id.homeFragment,
            R.id.searchFragment,
            R.id.favoriteFragment
        )
    )
    setupActionBarWithNavController(navController, appBarConfiguration)

    bottomNavigationView.setupWithNavController(navController)

}*/


}









