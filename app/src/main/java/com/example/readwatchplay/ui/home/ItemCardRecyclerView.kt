package com.example.readwatchplay.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.paris.extensions.style
import com.example.readwatchplay.R
import com.example.readwatchplay.model.Movie
import com.example.readwatchplay.ui.MainViewModel
import com.example.readwatchplay.ui.watched.WatchedFragment

class ItemCardRecyclerViewAdapter(
    private val movies: List<Movie>,
    private val watchedMoviesId: List<Int>,
    private val context: Fragment
) : RecyclerView.Adapter<ItemCardRecyclerViewAdapter.ItemCardViewHolder>() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        mainViewModel =
            ViewModelProviders.of(context).get(MainViewModel::class.java)
        return ItemCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ItemCardViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title


        // Watched button
        // Mark as watched
        if (containsId(watchedMoviesId, movie.id)) {
            holder.watchedButton.style(R.style.Widget_AppCompat_Button_Colored)
        }

        holder.watchedButton.setOnClickListener{  button ->
            if (containsId(watchedMoviesId, movie.id)) {
                button.style(R.style.Widget_AppCompat_Button)
                mainViewModel.removeMovieFromWatched(movie.id)
            } else {
                button.style(R.style.Widget_AppCompat_Button_Colored)
                mainViewModel.addMovieToWatched(movie.id)
            }
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



    private fun containsId(list: List<Int>, id: Int): Boolean {
        list.forEach {
            if (it == id) return true
        }
        return false
    }
}