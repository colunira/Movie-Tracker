package com.example.readwatchplay.ui.watched

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.readwatchplay.R
import com.example.readwatchplay.ui.itemcard.ItemCardGridDecoration
import com.example.readwatchplay.ui.itemcard.ItemCardRecyclerViewAdapter
import com.example.readwatchplay.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_watched.view.*

class WatchedFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mainViewModel =
                ViewModelProviders.of(this).get(MainViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_watched, container, false)

        // React on back pressed
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(WatchedFragmentDirections.actionNavigationDashboardToNavigationHome())
        }

        // Set up the RecyclerView
       mainViewModel.getWatchedMoviesIds().observe(viewLifecycleOwner, Observer { ids ->
            mainViewModel.getMoviesDetailsByIds(ids).observe(viewLifecycleOwner, Observer { movies ->
                view.watched_recycler_view.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
                val adapter =
                    ItemCardRecyclerViewAdapter(
                        movies,
                        ids.toMutableList(),
                        mutableListOf(),
                        this
                    )
                view.watched_recycler_view.adapter = adapter
            })
        })
        view.watched_recycler_view.setHasFixedSize(true)
        val largePadding = resources.getDimensionPixelSize(R.dimen.grid_spacing_large)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.grid_spacing_small)
        view.watched_recycler_view.addItemDecoration(
            ItemCardGridDecoration(
                largePadding,
                smallPadding
            )
        )

        return view

    }
}
