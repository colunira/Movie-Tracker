package com.example.readwatchplay.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.readwatchplay.data.Repository
import com.example.readwatchplay.model.Movie

class HomeViewModel : ViewModel() {

    private val repository: Repository = Repository()

    fun getTrendingMovies(): LiveData<List<Movie>> = repository.getTrendingMovies()

    fun getMovieDetails(id: Int): LiveData<Movie> = repository.getMovieDetails(id)


}