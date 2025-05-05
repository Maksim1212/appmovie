package com.example.appmovie

import com.example.appmovie.movie.common.Film

object FilmRepository {

    val films = arrayListOf(
        Film(
            cover = "@color/main_color_background_app",
            id = 0,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "12",
            data = 12
        ),
        Film(
            cover = "@color/main_color_background_app",
            id = 1,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "12",
            data = 12
        ),
        Film(
            cover = "@color/main_color_background_app",
            id = 2,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "12",
            data = 12
        )
    )
}
