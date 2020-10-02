package com.github.kinopoisk.app.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MovieDTO(
    val id: Int = 0,
    @Json(name = "localized_name")
    val localizedName: String = "",
    val name: String = "",
    val year: Int = 0,
    val rating: Float? = 0f,
    @Json(name = "image_url")
    val imageUrl: String? = "",
    val description: String = "",
    val genres: Set<String>
)
