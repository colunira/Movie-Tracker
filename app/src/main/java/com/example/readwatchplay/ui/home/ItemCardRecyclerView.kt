package com.example.readwatchplay.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.readwatchplay.R
import com.example.readwatchplay.model.Movie

class ItemCardRecyclerViewAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<ItemCardRecyclerViewAdapter.ItemCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return ItemCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ItemCardViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title

        holder.watchedButton.setOnClickListener{
            //TODO: add movie to watched list in the database
            // remove it from toWatch if it is possible
        }

        holder.toWatchButton.setOnClickListener {
            //TODO: add movie to the to watch list in the database
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ItemCardViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.product_title)
        val watchedButton = itemView.findViewById<ImageButton>(R.id.watched_button)
        val toWatchButton = itemView.findViewById<ImageButton>(R.id.to_watch_button)
    }
}