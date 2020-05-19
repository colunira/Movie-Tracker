package com.example.readwatchplay.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.readwatchplay.model.Movie
import com.example.readwatchplay.model.User
import com.google.android.gms.tasks.Tasks.await
import kotlinx.coroutines.*

class Repository {


    private val remote: Remote = Remote()
    private val database: Database = Database()


    fun addMovieToWatched(movieId: Int) = database.addMovieToWatched(movieId)

    fun removeMovieFromWatched(movieId: Int) = database.removeMovieFromWatched(movieId)

    fun getWatchedMoviesIds(): LiveData<List<Int>> = database.getWatchedIds()

    fun getMovieDetails(id: Int): LiveData<Movie> = remote.getMovieDetails(id)

    fun getTrendingMovies(): LiveData<List<Movie>> = remote.getTrendingMovies()

    fun getMoviesDetailsByIds(ids: List<Int>): LiveData<List<Movie>> = remote.getMoviesDetails(ids)

}