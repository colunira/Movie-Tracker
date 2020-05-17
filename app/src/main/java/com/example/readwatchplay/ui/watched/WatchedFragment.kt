package com.example.readwatchplay.ui.watched

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.readwatchplay.R
import com.example.readwatchplay.ui.home.ItemCardGridDecoration
import com.example.readwatchplay.ui.home.ItemCardRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_watched.view.*

class WatchedFragment : Fragment() {

    private lateinit var watchedViewModel: WatchedViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        watchedViewModel =
                ViewModelProviders.of(this).get(WatchedViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_watched, container, false)

        // Set up the RecyclerView
        watchedViewModel.addMovieToWatched(64)

        watchedViewModel.getWatchedMovies().observe(viewLifecycleOwner, Observer { movies ->
            //TODO: handle loading of elements
            view.watched_recycler_view.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            val adapter = ItemCardRecyclerViewAdapter(movies)
            view.watched_recycler_view.adapter = adapter
        })
        view.watched_recycler_view.setHasFixedSize(true)
        val largePadding = resources.getDimensionPixelSize(R.dimen.grid_spacing_large)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.grid_spacing_small)
        view.watched_recycler_view.addItemDecoration(ItemCardGridDecoration(largePadding, smallPadding))

        return view

    }
}
