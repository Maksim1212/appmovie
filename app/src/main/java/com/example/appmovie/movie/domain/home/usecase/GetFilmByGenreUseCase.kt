package com.example.appmovie.movie.domain.home.usecase

import com.example.appmovie.movie.data.remote.model.CollectionGenresResponse
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domain.home.entity.CategoriesFilmGenresEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFilmByGenreUseCase @Inject constructor(private val filmRepository: FilmRepository) {

    operator fun invoke(id: Int): Flow<List<CategoriesFilmGenresEntity>> =
        filmRepository.getFilmByGenre(id = id).map {
            it.convertToEntity()
        }

    private fun CollectionGenresResponse.convertToEntity(): List<CategoriesFilmGenresEntity> =
        this.items
            .filterNotNull()
            .mapIndexed { index, item ->
                CategoriesFilmGenresEntity(
                    id = item.kinopoiskId,
                    cover = item.posterUrlPreview,
                )
            }
}
