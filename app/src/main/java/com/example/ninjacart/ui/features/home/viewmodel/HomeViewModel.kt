package com.example.ninjacart.ui.features.home.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ninjacart.data.features.home.HomeRepository
import com.example.ninjacart.data.features.home.response.HomePageDataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val _homePageLd = MutableLiveData<HomePageDataStatus>()
    val homePageLd: LiveData<HomePageDataStatus> = _homePageLd

    // map for containing the ref of each points
    private val pointsMap = mutableMapOf<Double, View>()

    fun loadHomePageData() {
        viewModelScope.launch {
            _homePageLd.postValue(HomePageDataStatus.Loading)
            _homePageLd.postValue(homeRepository.getHomePageData())
        }
    }

    fun isPointAlreadyExist(key: Double) = pointsMap.containsKey(key)

    fun isNearByPointExist(key: Double): Boolean {
        var isExist = false
        pointsMap.forEach { (pointsKey, v) ->
            if (abs(pointsKey - key) <= 500.0) {
                isExist = true
                return@forEach
            }
        }
        return isExist
    }

    fun addPointsToMap(key: Double, view: View) {
        pointsMap[key] = view
    }
}
