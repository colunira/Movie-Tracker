package com.example.readwatchplay.ui.itemcard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.paris.extensions.style
import com.example.readwatchplay.R
import com.example.readwatchplay.model.Movie
import com.example.readwatchplay.ui.MainViewModel
import com.squareup.picasso.Picasso

class ItemCardRecyclerViewAdapter(
    private val movies: List<Movie>,
    private val watchedMoviesId: MutableList<Int>,
    private val toWatchMoviesId: MutableList<Int>,
    private val context: Fragment
) : RecyclerView.Adapter<ItemCardRecyclerViewAdapter.ItemCardViewHolder>() {

    private lateinit var mainViewModel: MainViewModel
    private val imagesURL = "https://image.tmdb.org/t/p/w300"
    private val picasso = Picasso.get()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        mainViewModel =
            ViewModelProviders.of(context).get(MainViewModel::class.java)
        return ItemCardViewHolder(
            layoutView
        )
    }

    override fun onBindViewHolder(holder: ItemCardViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        holder.score.text = "${movie.vote_average}/10"

        if (movie.backdrop_path != "") {
            picasso.load("${imagesURL}${movie.backdrop_path}").into(holder.poster)
        }

        // Watched button
        // Mark as watched
        if (watchedMoviesId.contains(movie.id)) {
            holder.watchedButton.style(R.style.Widget_AppCompat_Button_Colored)
        }

        holder.watchedButton.setOnClickListener{  button ->
            if (watchedMoviesId.contains(movie.id)) {
                button.style(R.style.Widget_AppCompat_Button)
                mainViewModel.removeMovieFromWatched(movie.id)
                watchedMoviesId.remove(movie.id)
            } else {
                button.style(R.style.Widget_AppCompat_Button_Colored)
                mainViewModel.addMovieToWatched(movie.id)
                watchedMoviesId.add(movie.id)
                if (toWatchMoviesId.contains(movie.id)) {
                    mainViewModel.removeMovieFromToWatch(movie.id)
                    toWatchMoviesId.remove(movie.id)
                    holder.toWatchButton.style(R.style.Widget_AppCompat_Button)
                }
            }
        }

        // To Watch button
        // mark as to watch
        if (toWatchMoviesId.contains(movie.id)) {
            holder.toWatchButton.style(R.style.Widget_AppCompat_Button_Colored)
        }
        holder.toWatchButton.setOnClickListener { button ->
            if (toWatchMoviesId.contains(movie.id)) {
                button.style(R.style.Widget_AppCompat_Button)
                mainViewModel.removeMovieFromToWatch(movie.id)
                toWatchMoviesId.remove(movie.id)
            } else {
                button.style(R.style.Widget_AppCompat_Button_Colored)
                mainViewModel.addMovieToToWatch(movie.id)
                toWatchMoviesId.add(movie.id)
                if (watchedMoviesId.contains(movie.id)) {
                    mainViewModel.removeMovieFromWatched(movie.id)
                    watchedMoviesId.remove(movie.id)
                    holder.watchedButton.style(R.style.Widget_AppCompat_Button)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ItemCardViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.product_title)
        val score = itemView.findViewById<TextView>(R.id.product_score)
        val watchedButton = itemView.findViewById<ImageButton>(R.id.watched_button)
        val toWatchButton = itemView.findViewById<ImageButton>(R.id.to_watch_button)
        val poster = itemView.findViewById<ImageView>(R.id.poster_image)
    }



    private fun containsId(list: List<Int>, id: Int): Boolean {
        list.forEach {
            if (it == id) return true
        }
        return false
    }
}