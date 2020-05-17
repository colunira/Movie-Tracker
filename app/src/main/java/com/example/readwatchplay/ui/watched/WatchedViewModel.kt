package com.example.readwatchplay.ui.watched

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.readwatchplay.data.Repository
import com.example.readwatchplay.model.Movie

class WatchedViewModel : ViewModel() {

    private val repository: Repository = Repository()

    fun getMoviesDetailsByIds(ids: List<Int>): LiveData<List<Movie>> = repository.getMoviesDetailsByIds(ids)

    fun getWatchedMovies(): LiveData<List<Movie>> = repository.getWatchedMovies()
    fun addMovieToWatched(movieId: Int) = repository.addMovieToWatched(movieId)
}