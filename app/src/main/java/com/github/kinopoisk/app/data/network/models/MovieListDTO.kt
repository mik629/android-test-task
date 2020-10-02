package com.github.kinopoisk.app.data.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MovieListDTO(val films: List<MovieDTO>)