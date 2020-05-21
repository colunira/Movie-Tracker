package com.example.readwatchplay.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.readwatchplay.R
import com.example.readwatchplay.ui.MainViewModel
import com.example.readwatchplay.ui.itemcard.ItemCardGridDecoration
import com.example.readwatchplay.ui.itemcard.ItemCardRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mainViewModel =
                ViewModelProviders.of(this).get(MainViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //React on back pressed
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            activity?.finish()
        }

        // Set up the RecyclerView

        mainViewModel.getTrendingMovies().observe(viewLifecycleOwner, Observer { movies ->
            mainViewModel.getWatchedMoviesIds().observe(viewLifecycleOwner, Observer { watchedIds ->
                mainViewModel.getToWatchMoviesIds().observe(viewLifecycleOwner, Observer { toWatchIds ->
                    view.recycler_view.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
                    val adapter =
                        ItemCardRecyclerViewAdapter(
                            movies,
                            watchedIds.toMutableList(),
                            toWatchIds.toMutableList(),
                            this
                        )
                    view.recycler_view.adapter = adapter
                })
            })
        })
        view.recycler_view.setHasFixedSize(true)
        val largePadding = resources.getDimensionPixelSize(R.dimen.grid_spacing_large)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.grid_spacing_small)
        view.recycler_view.addItemDecoration(
            ItemCardGridDecoration(
                largePadding,
                smallPadding
            )
        )

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }
}
