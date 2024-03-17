package com.example.ninjacart.ui.features.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ninjacart.data.features.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    fun loadHomePageData() {
        homeRepository.getHomePageData()
    }
}
