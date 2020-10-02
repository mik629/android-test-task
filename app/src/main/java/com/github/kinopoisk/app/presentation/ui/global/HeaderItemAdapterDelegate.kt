package com.github.kinopoisk.app.presentation.ui.global

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.kinopoisk.app.databinding.HeaderItemBinding
import com.github.kinopoisk.app.domain.models.HeaderItem
import com.github.kinopoisk.app.domain.models.MovieListItem
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class HeaderItemAdapterDelegate :
    AbsListItemAdapterDelegate<HeaderItem, MovieListItem, HeaderItemAdapterDelegate.ViewHolder>() {

    override fun isForViewType(item: MovieListItem, items: List<MovieListItem>, position: Int): Boolean {
        return item is HeaderItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(HeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(item: HeaderItem, holder: ViewHolder, payloads: List<Any>) {
        holder.updateView(item)
    }

    inner class ViewHolder(private val binding: HeaderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var item: HeaderItem

        fun updateView(item: HeaderItem) {
            this.item = item

            binding.headerText.text = item.header
        }
    }
}
