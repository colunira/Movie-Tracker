package com.example.readwatchplay.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.readwatchplay.model.User
import com.google.firebase.database.*


class Database {

    val database = FirebaseDatabase.getInstance().reference
    val currentUser = User()
    val watchedMoviesPath = database.child("users").child(currentUser.info!!.uid).child("watched")
    val toWatchMoviesPath = database.child("users").child(currentUser.info!!.uid).child("toWatch")


    fun addMovieToWatched(movieId: Int) {
        watchedMoviesPath.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var movieIdList = dataSnapshot.value as? MutableList<Int>
                if (movieIdList.isNullOrEmpty()) {
                    movieIdList = mutableListOf()
                }
                movieIdList.add(movieId)
                watchedMoviesPath.setValue(movieIdList)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun removeMovieFromWatched(movieId: Int) {
        watchedMoviesPath.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var key = ""
                for (row in dataSnapshot.children) {
                    if (row.value.toString() == movieId.toString()) {
                        key = row.key!!
                    }
                }
                watchedMoviesPath.child(key).removeValue()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.v("error", error.toString())
            }
        })
    }

    fun getWatchedMoviesIds(): LiveData<List<Int>> {
        val movies = MutableLiveData<List<Int>>()

        watchedMoviesPath.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val movieIdList = mutableListOf<Int?>()
                for (row in dataSnapshot.children) {
                    val id = row.getValue(Int::class.java)
                    movieIdList.add(id)
                }
                movies.value = movieIdList.filterNotNull()
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        return movies
    }

    fun addMovieToToWatch(movieId: Int) {
        toWatchMoviesPath.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var movieIdList = dataSnapshot.value as? MutableList<Int>
                if (movieIdList.isNullOrEmpty()) {
                    movieIdList = mutableListOf()
                }
                movieIdList.add(movieId)
                toWatchMoviesPath.setValue(movieIdList)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun removeMovieFromToWatch(movieId: Int) {
        toWatchMoviesPath.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var key = ""
                for (row in dataSnapshot.children) {
                    if (row.value.toString() == movieId.toString()) {
                        key = row.key!!
                    }
                }
                toWatchMoviesPath.child(key).removeValue()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.v("error", error.toString())
            }
        })
    }

    fun getToWatchMoviesIds(): LiveData<List<Int>> {
        val movies = MutableLiveData<List<Int>>()

        toWatchMoviesPath.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val movieIdList = mutableListOf<Int?>()
                for (row in dataSnapshot.children) {
                    val id = row.getValue(Int::class.java)
                    movieIdList.add(id)
                }
                movies.value = movieIdList.filterNotNull()
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        return movies
    }
}