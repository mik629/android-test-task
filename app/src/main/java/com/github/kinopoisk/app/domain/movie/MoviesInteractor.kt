package com.github.kinopoisk.app.domain.movie

import com.github.kinopoisk.app.domain.models.MovieItem
import com.github.kinopoisk.app.domain.repositories.MoviesRepository
import javax.inject.Inject

class MoviesInteractor @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend fun getMovies(genre: String?): List<MovieItem> = moviesRepository.getMovies(genre)

    fun getMovie(id: Int): MovieItem =
        moviesRepository
            .getMovie(id)
}
