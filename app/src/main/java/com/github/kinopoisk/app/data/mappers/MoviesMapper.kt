package com.github.kinopoisk.app.data.mappers

import com.github.kinopoisk.app.data.network.models.MovieDTO
import com.github.kinopoisk.app.domain.models.MovieItem
import javax.inject.Inject

class MoviesMapper @Inject
constructor() : Mapper<MovieDTO, MovieItem> {

    override fun map(obj: MovieDTO): MovieItem =
        MovieItem(
            obj.id,
            obj.localizedName,
            obj.name,
            obj.year,
            obj.rating,
            obj.imageUrl,
            obj.description,
            obj.genres
        )

    override fun reverseMap(obj: MovieItem): MovieDTO =
        MovieDTO(
            obj.id,
            obj.localizedName,
            obj.name,
            obj.year,
            obj.rating,
            obj.imageUrl,
            obj.description,
            obj.genres
        )
}
