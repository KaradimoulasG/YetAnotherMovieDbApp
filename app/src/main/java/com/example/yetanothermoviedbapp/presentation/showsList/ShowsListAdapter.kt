package com.example.yetanothermoviedbapp.presentation.showsList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.yetanothermoviedbapp.R
import com.example.yetanothermoviedbapp.domain.models.ShowsListItem

class ShowsListAdapter(
    private val layoutInflater: LayoutInflater,
    private val scrolledToEnd: () -> Unit,
    private val onShowsClick: (Int) -> Unit
) : ListAdapter<ShowsListItem, ShowsListAdapter.ShowsViewHolder>(DiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ShowsViewHolder(layoutInflater, parent, onShowsClick)
    override fun onBindViewHolder(holder: ShowsViewHolder, position: Int) = holder.bind(holder, position)
    fun clear() = submitList(null)
    fun addAll(items: List<ShowsListItem>) = submitList(items)

    private class DiffCallBack : DiffUtil.ItemCallback<ShowsListItem>() {
        override fun areItemsTheSame(oldItem: ShowsListItem, newItem: ShowsListItem) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: ShowsListItem, newItem: ShowsListItem) = oldItem == newItem
    }

    inner class ShowsViewHolder(
        layoutInflater: LayoutInflater,
        parentView: ViewGroup,
        private val onShowsClick: (Int) -> Unit
    ) : ViewHolder(layoutInflater.inflate(R.layout.shows_list_adapter_item, parentView, false)) {
        private var moviePoster: ImageView = itemView.findViewById(R.id.movie_poster_iv)
        private var movieTitle: TextView = itemView.findViewById(R.id.movie_title_tv)
        private var rating: RatingBar = itemView.findViewById(R.id.ratingBar)
        private var releaseDate: TextView = itemView.findViewById(R.id.release_date_iv)


        fun bind(holder: ShowsViewHolder, pos: Int) {

            val items = currentList[pos]
            val context = holder.itemView.context
            if (pos == itemCount - 7) scrolledToEnd()

            Glide.with(context).load(items.image.original).into(holder.moviePoster)

            movieTitle.text = items.name
            rating.rating = items.rating.average.toFloat() / 2
        }

    }
}