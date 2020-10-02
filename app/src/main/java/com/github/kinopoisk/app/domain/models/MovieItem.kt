package com.github.kinopoisk.app.domain.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MovieItem(
    val id: Int = 0,
    val localizedName: String = "",
    val name: String = "",
    val year: Int = 0,
    val rating: Float? = 0f,
    val imageUrl: String? = "",
    val description: String = "",
    val genres: Set<String>
) : MovieListItem {
    override val type: Int = 2
}
