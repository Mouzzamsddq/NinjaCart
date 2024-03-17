package com.example.ninjacart.data.features.home.source

import com.example.ninjacart.data.features.home.response.Home
import com.example.ninjacart.data.features.home.response.HomePageDataStatus
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val firebaseDb: FirebaseDatabase,
) {
    suspend fun getHomePageData(): HomePageDataStatus {
        return withContext(Dispatchers.IO) {
            try {
                val dataSnapshot = firebaseDb.reference.child("home").get().await()
                if (dataSnapshot.exists()) {
                    val homeData = dataSnapshot.getValue(Home::class.java)
                    HomePageDataStatus.Success(homeData)
                } else {
                    HomePageDataStatus.Error("Failed to load data")
                }
            } catch (e: Exception) {
                HomePageDataStatus.Error("Error: ${e.message}")
            }
        }
    }
}
