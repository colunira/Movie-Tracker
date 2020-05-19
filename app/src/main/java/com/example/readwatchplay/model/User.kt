package com.example.readwatchplay.model

import android.os.Parcelable
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.parcel.Parcelize

@Parcelize
class User: Parcelable {

    val info = FirebaseAuth.getInstance().currentUser

    companion object {
        val instance = User()
    }
}