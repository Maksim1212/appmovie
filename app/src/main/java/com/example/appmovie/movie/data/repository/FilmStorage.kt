package com.example.appmovie.movie.data.repository

import com.example.appmovie.movie.data.FilmModel
import com.example.appmovie.movie.data.RankedFilmModel
import com.example.appmovie.movie.data.CategoriesFilmsModel

object FilmStorage {
    val selectedFilms = arrayListOf(
        FilmModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/6ac42bf1-53e1-46c0-856f-31ab8373b974/1920x",
            id = 0,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        FilmModel(
            cover = "https://upload.wikimedia.org/wikipedia/ru/6/6e/Spider-Man_—_No_Way_Home_poster.jpg",
            id = 1,
            header = "Spiderman1",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        FilmModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/458a7588-35f6-4928-bffa-e38704e75e26/1920x",
            id = 2,
            header = "Spiderman2",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        FilmModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/6ac42bf1-53e1-46c0-856f-31ab8373b974/1920x",
            id = 0,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
    )
    val topRankedFilms = arrayListOf(
        RankedFilmModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/6ac42bf1-53e1-46c0-856f-31ab8373b974/1920x",
            id = 0,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019,
            rank = 1
        ),
        RankedFilmModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/4303601/4e48d64d-4c27-4adf-b6c7-5061fc2ce965/1920x",
            id = 1,
            header = "Spiderman1",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019,
            rank = 2
        ),
        RankedFilmModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/458a7588-35f6-4928-bffa-e38704e75e26/1920x",
            id = 2,
            header = "Spiderman2",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019,
            rank = 3
        ),
        RankedFilmModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/6ac42bf1-53e1-46c0-856f-31ab8373b974/1920x",
            id = 0,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019,
            rank = 4
        ),
    )
    val popularFilms = arrayListOf(
        CategoriesFilmsModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/6ac42bf1-53e1-46c0-856f-31ab8373b974/1920x",
            id = 0,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        CategoriesFilmsModel(
            cover = "https://upload.wikimedia.org/wikipedia/ru/6/6e/Spider-Man_—_No_Way_Home_poster.jpg",
            id = 1,
            header = "Spiderman1",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        CategoriesFilmsModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/4303601/4e48d64d-4c27-4adf-b6c7-5061fc2ce965/1920x",
            id = 2,
            header = "Spiderman2",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        CategoriesFilmsModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/6ac42bf1-53e1-46c0-856f-31ab8373b974/1920x",
            id = 0,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
    )
    val recommendedFilms = arrayListOf(
        CategoriesFilmsModel(
            cover = "https://upload.wikimedia.org/wikipedia/ru/6/6e/Spider-Man_—_No_Way_Home_poster.jpg",
            id = 0,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        CategoriesFilmsModel(
            cover = "https://upload.wikimedia.org/wikipedia/ru/6/6e/Spider-Man_—_No_Way_Home_poster.jpg",
            id = 1,
            header = "Spiderman1",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        CategoriesFilmsModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/4303601/4e48d64d-4c27-4adf-b6c7-5061fc2ce965/1920x",
            id = 2,
            header = "Spiderman2",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        CategoriesFilmsModel(
            cover = "https://upload.wikimedia.org/wikipedia/ru/6/6e/Spider-Man_—_No_Way_Home_poster.jpg",
            id = 0,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
    )
    val newFilms = arrayListOf(
        CategoriesFilmsModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/6ac42bf1-53e1-46c0-856f-31ab8373b974/1920x",
            id = 0,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        CategoriesFilmsModel(
            cover = "https://upload.wikimedia.org/wikipedia/ru/6/6e/Spider-Man_—_No_Way_Home_poster.jpg",
            id = 1,
            header = "Spiderman1",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        CategoriesFilmsModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/458a7588-35f6-4928-bffa-e38704e75e26/1920x",
            id = 2,
            header = "Spiderman2",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        CategoriesFilmsModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/6ac42bf1-53e1-46c0-856f-31ab8373b974/1920x",
            id = 0,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
    )
    val theBestFilms = arrayListOf(
        CategoriesFilmsModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/6ac42bf1-53e1-46c0-856f-31ab8373b974/1920x",
            id = 0,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        CategoriesFilmsModel(
            cover = "https://upload.wikimedia.org/wikipedia/ru/6/6e/Spider-Man_—_No_Way_Home_poster.jpg",
            id = 1,
            header = "Spiderman1",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        CategoriesFilmsModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/458a7588-35f6-4928-bffa-e38704e75e26/1920x",
            id = 2,
            header = "Spiderman2",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
        CategoriesFilmsModel(
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/6ac42bf1-53e1-46c0-856f-31ab8373b974/1920x",
            id = 0,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes",
            rating = "9.5",
            data = 2019
        ),
    )
}
