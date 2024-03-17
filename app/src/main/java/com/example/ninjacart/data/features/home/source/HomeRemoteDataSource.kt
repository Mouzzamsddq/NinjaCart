package com.example.ninjacart.data.features.home.source

import com.example.ninjacart.data.features.home.response.HomePageDataStatus
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val firebaseDb: FirebaseDatabase,
) {
    fun getHomePageData(): HomePageDataStatus {
        firebaseDb.reference.get()
            .addOnSuccessListener {
            }
            .addOnFailureListener {
            }
        return HomePageDataStatus.Loading
    }
}
