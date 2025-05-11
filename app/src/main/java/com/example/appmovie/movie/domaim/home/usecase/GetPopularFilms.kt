package com.example.appmovie.movie.domaim.home.usecase

import com.example.appmovie.movie.data.PopularFilmsModel
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.entity.PopularFilmsEntity

class GetPopularFilms(private val filmRepository: FilmRepository) {

    operator fun invoke(): List<PopularFilmsEntity> {
        val models = filmRepository.getPopularFilms()
        return models.map {
            convertPopularFilmModelToEntity(it)
        }

    }

    private fun convertPopularFilmModelToEntity(
        popularFilmsModel: PopularFilmsModel
    ): PopularFilmsEntity = PopularFilmsEntity(
        id = popularFilmsModel.id,
        cover = popularFilmsModel.cover
    )

}
