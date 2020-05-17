package com.example.readwatchplay.data

import android.util.Log
import com.example.readwatchplay.model.User
import com.google.firebase.database.*


class Database {
    val database = FirebaseDatabase.getInstance().reference
    val currentUser = User()
    val userWatchedPath = database.child("users").child(currentUser.info!!.uid).child("watched")

    fun addMovieToWatched(movieId: Int) {
        userWatchedPath.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var movieIdList = dataSnapshot.value as? MutableList<Int>
                Log.v("dupa", movieIdList.toString())
                if (movieIdList.isNullOrEmpty()) {
                    movieIdList = mutableListOf()
                }
                movieIdList.add(movieId)
                userWatchedPath.setValue(movieIdList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("dupa", "Failed to read value.", error.toException())
            }
        })

    }

    fun getWatchedIds(): List<Int> {
        //TODO: implement fetching watched id's
//        myRef.setValue("Hello, World!")
//
//        // Read from the database
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                val value = dataSnapshot.getValue()
//                Log.d("DBkul", "Value is: $value")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//                Log.w("DBkul", "Failed to read value.", error.toException())
//            }
//        })
        return listOf(3,6)
    }


}