package com.example.readwatchplay.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.readwatchplay.data.Repository
import com.example.readwatchplay.model.Movie
import com.example.readwatchplay.model.User

class MainViewModel : ViewModel() {

    private val repository: Repository = Repository()

    fun getTrendingMovies(): LiveData<List<Movie>> = repository.getTrendingMovies()

    fun getMovieDetails(id: Int): LiveData<Movie> = repository.getMovieDetails(id)

    fun getMoviesDetailsByIds(ids: List<Int>): LiveData<List<Movie>> = repository.getMoviesDetailsByIds(ids)

    fun getWatchedMoviesIds(): LiveData<List<Int>> = repository.getWatchedMoviesIds()

    fun addMovieToWatched(movieId: Int) = repository.addMovieToWatched(movieId)

    fun removeMovieFromWatched(movieId: Int) = repository.removeMovieFromWatched(movieId)

    fun getToWatchMoviesIds(): LiveData<List<Int>> = repository.getToWatchMoviesIds()

    fun addMovieToToWatch(movieId: Int) = repository.addMovieToToWatch(movieId)

    fun removeMovieFromToWatch(movieId: Int) = repository.removeMovieFromToWatch(movieId)
}