package com.github.kinopoisk.app.presentation.ui.movielist

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.kinopoisk.app.GlideRequest
import com.github.kinopoisk.app.R
import com.github.kinopoisk.app.domain.models.GenreItem
import com.github.kinopoisk.app.domain.models.MovieItem
import com.github.kinopoisk.app.domain.models.MovieListItem
import com.github.kinopoisk.app.presentation.ui.global.GenreItemAdapterDelegate
import com.github.kinopoisk.app.presentation.ui.global.HeaderItemAdapterDelegate
import com.github.kinopoisk.app.presentation.ui.global.MovieItemAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MovieListDelegateAdapter(
    glideRequest: GlideRequest<Drawable>,
    onGenreClickListener: (GenreItem) -> Unit,
    onMovieClickListener: (MovieItem) -> Unit
) : AsyncListDifferDelegationAdapter<MovieListItem>(DIFF_CALLBACK), ListPreloader.PreloadModelProvider<MovieItem> {

    private val glideRequest: GlideRequest<Drawable> = glideRequest
            .thumbnail(0.1f)
            .placeholder(R.drawable.ic_image_loading)
            .fallback(R.drawable.ic_broken_image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .fitCenter()

    init {
        delegatesManager
                .addDelegate(GenreItemAdapterDelegate(onGenreClickListener))
                .addDelegate(HeaderItemAdapterDelegate())
                .addDelegate(MovieItemAdapterDelegate(this.glideRequest, onMovieClickListener))
    }

    override fun getPreloadItems(position: Int): List<MovieItem> {
        if (position >= itemCount)
            return emptyList()
        return items
                .subList(position, items.lastIndex)
                .asSequence()
                .filter { it is MovieItem && !it.imageUrl.isNullOrEmpty() }
                .take(5)
                .map { it as MovieItem }
                .toList()
    }

    override fun getPreloadRequestBuilder(item: MovieItem): RequestBuilder<*>? {
        return glideRequest.load(item.imageUrl)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieListItem>() {
            override fun areItemsTheSame(oldItem: MovieListItem, newItem: MovieListItem): Boolean {
                return when {
                    oldItem is MovieItem && newItem is MovieItem -> oldItem.id == newItem.id
                    oldItem is GenreItem && newItem is GenreItem -> oldItem.name == newItem.name
                    else -> oldItem.type == newItem.type
                }
            }

            override fun areContentsTheSame(oldItem: MovieListItem, newItem: MovieListItem): Boolean {
                return when {
                    oldItem is MovieItem && newItem is MovieItem -> oldItem == newItem
                    oldItem is GenreItem && newItem is GenreItem -> oldItem == newItem
                    else -> oldItem.type == newItem.type
                }
            }
        }
    }
}
