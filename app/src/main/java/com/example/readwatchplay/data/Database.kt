package com.example.readwatchplay.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.readwatchplay.model.User
import com.google.firebase.database.*


class Database {

    companion object {
        val database = FirebaseDatabase.getInstance().reference
        val currentUser = User()
        val watchesMoviesPath = database.child("users").child(currentUser.info!!.uid).child("watched")
    }

    fun addMovieToWatched(movieId: Int) {
        watchesMoviesPath.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var movieIdList = dataSnapshot.value as? MutableList<Int>
                if (movieIdList.isNullOrEmpty()) {
                    movieIdList = mutableListOf()
                }
                movieIdList.add(movieId)
                watchesMoviesPath.setValue(movieIdList)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

    fun removeMovieFromWatched(movieId: Int) {
        watchesMoviesPath.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val movieIdList = mutableListOf<Int?>()
                for (row in dataSnapshot.children) {
                    val id = row.getValue(Int::class.java)
                    movieIdList.add(id)
                }
                val id = movieIdList.indexOf(movieId)
                watchesMoviesPath.child(id.toString()).removeValue()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.v("error", error.toString())
            }
        })
    }

    fun getWatchedIds(): LiveData<List<Int>> {
        val movies = MutableLiveData<List<Int>>()

        watchesMoviesPath.addListenerForSingleValueEvent(object : ValueEventListener {
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


    //TODO: duplicate the functionality for "to watch"
}