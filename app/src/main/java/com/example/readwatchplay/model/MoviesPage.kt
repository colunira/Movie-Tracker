package com.example.readwatchplay.model

data class MoviesPage(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)