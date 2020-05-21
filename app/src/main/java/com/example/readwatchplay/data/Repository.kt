package com.example.readwatchplay.data

import androidx.lifecycle.LiveData
import com.example.readwatchplay.model.Movie

class Repository {


    private val remote: Remote = Remote()
    private val database: Database = Database()

    fun getMovieDetails(id: Int): LiveData<Movie> = remote.getMovieDetails(id)

    fun getTrendingMovies(): LiveData<List<Movie>> = remote.getTrendingMovies()

    fun getMoviesDetailsByIds(ids: List<Int>): LiveData<List<Movie>> = remote.getMoviesDetails(ids)

    fun getWatchedMoviesIds(): LiveData<List<Int>> = database.getWatchedMoviesIds()

    fun addMovieToWatched(movieId: Int) = database.addMovieToWatched(movieId)

    fun removeMovieFromWatched(movieId: Int) = database.removeMovieFromWatched(movieId)

    fun getToWatchMoviesIds(): LiveData<List<Int>> = database.getToWatchMoviesIds()

    fun addMovieToToWatch(movieId: Int) = database.addMovieToToWatch(movieId)

    fun removeMovieFromToWatch(movieId: Int) = database.removeMovieFromToWatch(movieId)

}