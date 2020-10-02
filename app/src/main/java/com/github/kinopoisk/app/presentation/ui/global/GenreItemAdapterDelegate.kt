package com.github.kinopoisk.app.presentation.ui.global

 import android.view.LayoutInflater
 import android.view.ViewGroup
 import androidx.recyclerview.widget.RecyclerView
 import com.github.kinopoisk.app.databinding.GenreItemBinding
 import com.github.kinopoisk.app.domain.models.GenreItem
 import com.github.kinopoisk.app.domain.models.MovieListItem
 import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class GenreItemAdapterDelegate(
    private val clickListener: (GenreItem) -> Unit
) : AbsListItemAdapterDelegate<GenreItem, MovieListItem, GenreItemAdapterDelegate.ViewHolder>() {

    override fun isForViewType(item: MovieListItem, items: List<MovieListItem>, position: Int): Boolean {
        return item is GenreItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(item: GenreItem, holder: ViewHolder, payloads: List<Any>) {
        holder.updateView(item)
    }

    inner class ViewHolder(private val binding: GenreItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        lateinit var item: GenreItem

        fun updateView(item: GenreItem) {
            this.item = item

            with(binding.genreBtn) {
                text = item.name
                setOnClickListener { clickListener(item) }
            }
        }
    }
}
