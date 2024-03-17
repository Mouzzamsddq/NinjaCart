package com.example.ninjacart.ui.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ninjacart.data.features.home.HomeRepository
import com.example.ninjacart.data.features.home.response.HomePageDataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val _homePageLd = MutableLiveData<HomePageDataStatus>()
    val homePageLd: LiveData<HomePageDataStatus> = _homePageLd

    fun loadHomePageData() {
        viewModelScope.launch {
            _homePageLd.postValue(homeRepository.getHomePageData())
        }
    }
}
