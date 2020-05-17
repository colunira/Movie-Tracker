package com.example.readwatchplay.model

import com.google.firebase.auth.FirebaseAuth


class User {

    val info = FirebaseAuth.getInstance().currentUser

    companion object {
        val instance = User()
    }
}