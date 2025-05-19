package com.example.appmovie.movie.domaim.home

import com.example.appmovie.movie.data.CategoriesFilmsModel
import com.example.appmovie.movie.domaim.home.entity.CategoriesFilmEntity

object CategoriesFilmModelConverter {

    internal fun convertCategoriesFilmsModelToEntity(
        categoriesFilmsModel: CategoriesFilmsModel
    ): CategoriesFilmEntity = CategoriesFilmEntity(
        id = categoriesFilmsModel.id,
        cover = categoriesFilmsModel.cover
    )
}
