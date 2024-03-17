package com.example.ninjacart.data.features.home.response

sealed class HomePageDataStatus {
    data class Success(val home: Home?) : HomePageDataStatus()
    data class Error(val error: String) : HomePageDataStatus()
    object Loading : HomePageDataStatus()
}
