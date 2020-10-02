package com.github.kinopoisk.app.presentation.ui.global

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.kinopoisk.app.GlideRequest
import com.github.kinopoisk.app.databinding.MovieItemBinding
import com.github.kinopoisk.app.domain.models.MovieItem
import com.github.kinopoisk.app.domain.models.MovieListItem
import com.github.kinopoisk.app.extensions.changeVisibility
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class MovieItemAdapterDelegate(
    private val glideRequest: GlideRequest<Drawable>,
    private val clickListener: (MovieItem) -> Unit
) : AbsListItemAdapterDelegate<MovieItem, MovieListItem, MovieItemAdapterDelegate.ViewHolder>() {

    override fun isForViewType(item: MovieListItem, items: List<MovieListItem>, position: Int): Boolean {
        return item is MovieItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(item: MovieItem, holder: ViewHolder, payloads: List<Any>) {
        holder.updateView(item)
    }

    inner class ViewHolder(private val binding: MovieItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        lateinit var item: MovieItem

        init {
            binding.root.setOnClickListener { clickListener(item) }
        }

        fun updateView(item: MovieItem) {
            this.item = item

            with(binding) {
                movieImage.apply {
                    changeVisibility(!item.imageUrl.isNullOrEmpty())
                    if (!item.imageUrl.isNullOrEmpty()) {
                        glideRequest.fitCenter().load(item.imageUrl).into(this)
                    }
                }
                movieTitle.apply {
                    changeVisibility(item.localizedName.isNotEmpty())
                    text = item.localizedName
                }
            }
        }
    }
}
