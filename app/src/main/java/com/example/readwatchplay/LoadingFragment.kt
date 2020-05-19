package com.example.readwatchplay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.readwatchplay.ui.MainViewModel

class LoadingFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel =
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_loading, container, false)

        // TODO: setup the loading page

        return view

    }
}
