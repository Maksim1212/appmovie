package com.example.appmovie.movie.data.repository.repository

import com.example.appmovie.Film

object FilmRepository {
    var films = arrayListOf(
        Film(
            cover = "https://yandex.ru/images/search?from=tabbar&img_url=https%3A%2F%2Frare-gallery.com%2Fmocahbig%2F1422560-spiderman-superheroes-movies-hd-4k-5k.jpg&lr=37134&p=1&pos=16&rpt=simage&text=заставки%20на%20фильм%20человека%20паука",
            id = 0,
            header = "Spiderman",
            genre = "Action",
            time = "139 minutes"
        ),
        Film(
            cover = "https://yandex.ru/images/search?from=tabbar&img_url=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fru%2Fthumb%2F6%2F6e%2FSpider-Man_%25E2%2580%2594_No_Way_Home_poster.jpg%2F480px-Spider-Man_%25E2%2580%2594_No_Way_Home_poster.jpg&lr=37134&pos=1&rpt=simage&text=заставки%20на%20фильм%20человека%20паука%20нет%20пути%20назад",
            id = 1,
            header = "Spiderman1",
            genre = "Action",
            time = "139 minutes"
        ),
        Film(
            cover = "https://yandex.ru/images/search?img_url=https%3A%2F%2Fblogger.googleusercontent.com%2Fimg%2Fb%2FR29vZ2xl%2FAVvXsEiE8d35NZh0GjX60oYoxgTuxh6TRRVhcYFtnNGPJx2wlmM7CUwubob-RwuFiWwIY99B5MBWDUxC4M4t3KPqTYXPyiQF_fBsH6bMmEFcs_RG4KnSoTyC4u9AK0BFKJE8sxHsxhZuGhiAK8I7koqeh8uRMvrEgItEwHsoZhCPJdtmUqR_pufHfP0lKNGX%2Fs3000%2F940e286441aca1e00a856316acf003723a9768c0366d5c85e5b2fa8f60f46cb7._RI_TTW_.jpg&lr=37134&pos=0&rpt=simage&source=serp&text=заставки%20на%20фильм%20человека%20паука%20враг%20в%20отражении",
            id = 2,
            header = "Spiderman2",
            genre = "Action",
            time = "139 minutes"
        )
    )
}
