package com.github.kinopoisk.app.domain.repositories

import com.github.kinopoisk.app.domain.models.MovieItem

interface MoviesRepository {
    fun getMovie(id: Int): MovieItem

    suspend fun getMovies(genre: String?): List<MovieItem>
}
