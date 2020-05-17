package com.example.readwatchplay.data

import androidx.lifecycle.LiveData
import com.example.readwatchplay.model.Movie

class Repository {

    private val remote: Remote = Remote()
    private val database: Database = Database()

    fun addMovieToWatched(movieId: Int) = database.addMovieToWatched(movieId)
    fun getWatchedMovies(): LiveData<List<Movie>> {
        val ids = database.getWatchedIds()
        return getMoviesDetailsByIds(ids)
    }

    fun getMovieDetails(id: Int): LiveData<Movie> = remote.getMovieDetails(id)

    fun getTrendingMovies(): LiveData<List<Movie>> = remote.getTrendingMovies()

    fun getMoviesDetailsByIds(ids: List<Int>): LiveData<List<Movie>> = remote.getMoviesDetails(ids)

}