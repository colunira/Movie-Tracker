package com.example.readwatchplay.data

import com.example.readwatchplay.model.Movie
import com.example.readwatchplay.model.MoviesPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int, @Query("api_key") key: String): Call<Movie>

    @GET("trending/movie/week")
    fun getTrendingMovies(@Query("api_key") key: String): Call<MoviesPage>


}