package com.example.readwatchplay.model

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.parcel.Parcelize

class User {

    val info = FirebaseAuth.getInstance().currentUser

    companion object {
        val instance = User()
    }
}