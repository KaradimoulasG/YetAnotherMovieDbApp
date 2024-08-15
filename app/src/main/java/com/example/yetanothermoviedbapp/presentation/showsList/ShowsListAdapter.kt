package com.example.yetanothermoviedbapp.presentation.showsList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.yetanothermoviedbapp.R
import com.example.yetanothermoviedbapp.domain.models.ShowsListItem

class ShowsListAdapter(
    private val layoutInflater: LayoutInflater,
    private val onShowClick: (Int) -> Unit
) : ListAdapter<ShowsListItem, ShowsListAdapter.ShowsViewHolder>(DiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ShowsViewHolder(layoutInflater, parent)
    override fun onBindViewHolder(holder: ShowsViewHolder, position: Int) = holder.bind(holder, position)
    fun clear() = submitList(null)
    fun addAll(items: List<ShowsListItem>) = submitList(items)

    private class DiffCallBack : DiffUtil.ItemCallback<ShowsListItem>() {
        override fun areItemsTheSame(oldItem: ShowsListItem, newItem: ShowsListItem) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: ShowsListItem, newItem: ShowsListItem) = oldItem == newItem
    }

    inner class ShowsViewHolder(
        layoutInflater: LayoutInflater,
        parentView: ViewGroup
    ) : ViewHolder(layoutInflater.inflate(R.layout.shows_list_adapter_item, parentView, false)) {
        private var showPoster: ImageView = itemView.findViewById(R.id.show_poster_iv)
        private var showTitle: TextView = itemView.findViewById(R.id.show_title_tv)
        private var rating: RatingBar = itemView.findViewById(R.id.ratingBar)
        private var posterCard: CardView = itemView.findViewById(R.id.poster_card)

        fun bind(holder: ShowsViewHolder, pos: Int) {
            val item = currentList[pos]
            val context = holder.itemView.context

            Glide.with(context).load(item.image.original).into(holder.showPoster)

            showTitle.text = item.name
            rating.rating = item.rating.average.toFloat() / 2

            posterCard.setOnClickListener { onShowClick(item.id) }
        }

    }
}