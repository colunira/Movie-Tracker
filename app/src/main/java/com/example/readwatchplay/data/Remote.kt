package com.example.readwatchplay.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.readwatchplay.model.Movie
import com.example.readwatchplay.model.MoviesPage
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Remote {
    private val moviesRetrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
    private val moviesAPI = moviesRetrofit.create(MoviesAPI::class.java)
    private val imagesRetrofit = Retrofit.Builder().baseUrl("https://image.tmdb.org/t/p/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
    private val imagesAPI = imagesRetrofit.create(ImagesAPI::class.java)
    private val key = ""

    fun getMovieDetails(id: Int): LiveData<Movie> {
        val movieDetails = MutableLiveData<Movie>()
        val call = moviesAPI.getMovieDetails(id, key)

        call.enqueue(object: Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    movieDetails.value = response.body()
                } else {
                    movieDetails.value = null
                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                movieDetails.value = null
            }
        })
        return movieDetails
    }

    fun getMoviesDetails(ids: List<Int>): LiveData<List<Movie>> {
        val moviesList = mutableListOf<Movie>()
        val movies = MutableLiveData<List<Movie>>()

        for (id in ids) {
            val call = moviesAPI.getMovieDetails(id, key)
            call.enqueue(object: Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful) {
                        val movie = response.body()!!
                        moviesList.add(movie)
                        movies.value = moviesList
                    } else {
                        moviesList.add(Movie())
                    }
                }
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    moviesList.add(Movie())
                }
            })
        }


        return movies
    }

    fun getTrendingMovies(): LiveData<List<Movie>> {
        val movies = MutableLiveData<List<Movie>>()
        val call = moviesAPI.getTrendingMovies(key)

        call.enqueue(object: Callback<MoviesPage> {
            override fun onResponse(call: Call<MoviesPage>, response: Response<MoviesPage>) {
                if (response.isSuccessful) {
                    movies.value = response.body()!!.results
                } else {
                    movies.value = null
                }
            }
            override fun onFailure(call: Call<MoviesPage>, t: Throwable) {
                movies.value = null
            }
        })
        return movies
    }
}