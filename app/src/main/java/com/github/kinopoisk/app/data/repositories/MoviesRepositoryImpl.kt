package com.github.kinopoisk.app.data.repositories

import android.util.SparseArray
import com.github.kinopoisk.app.data.mappers.Mapper
import com.github.kinopoisk.app.data.network.ServerApi
import com.github.kinopoisk.app.data.network.models.MovieDTO
import com.github.kinopoisk.app.domain.models.MovieItem
import com.github.kinopoisk.app.domain.repositories.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val serverApi: ServerApi,
    private val mapper: Mapper<MovieDTO, MovieItem>
) : MoviesRepository {

    private val moviesCache = SparseArray<MovieItem>()

    override fun getMovie(id: Int): MovieItem =
        moviesCache[id]

    override suspend fun getMovies(genre: String?): List<MovieItem> =
            serverApi.getMovies()
                .films
                .map {
                    val movieItem = mapper.map(it)
                    moviesCache.append(movieItem.id, movieItem)
                    movieItem
                }
                .filter { genre.isNullOrEmpty() || it.genres.contains(genre) }
}
