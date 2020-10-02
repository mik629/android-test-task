package com.github.kinopoisk.app.data.network

import com.github.kinopoisk.app.data.network.models.MovieListDTO
import retrofit2.http.GET

interface ServerApi {

    @GET("/sequeniatesttask/films.json")
    suspend fun getMovies(): MovieListDTO
}
