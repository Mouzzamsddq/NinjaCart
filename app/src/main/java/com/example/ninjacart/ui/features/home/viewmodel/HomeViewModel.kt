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

    private val _finalPrice = MutableLiveData<Double>()
    val finalPrice: LiveData<Double> = _finalPrice

    // map for containing the ref of each points
    private val pointsMap = mutableMapOf<Double, View>()

    init {
        loadHomePageData()
    }

    private fun loadHomePageData() {
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

    fun incrementQty(pos: Int) {
        (_homePageLd.value as? HomePageDataStatus.Success)?.let {
            it.home?.items?.getOrNull(pos)?.let {
                it.boughtQuantity += it.multiple
            }
            _homePageLd.postValue(it)
        }
    }

    fun decrementQty(pos: Int) {
        (_homePageLd.value as? HomePageDataStatus.Success)?.let {
            it.home?.items?.getOrNull(pos)?.let {
                if (it.boughtQuantity - it.multiple >= 0) {
                    it.boughtQuantity -= it.multiple
                } else {
                    _homePageLd.postValue(HomePageDataStatus.Error("Invalid quantity...!"))
                }
            }
            _homePageLd.postValue(it)
        }
    }

    fun calculateFinalPrice() {
        (_homePageLd.value as? HomePageDataStatus.Success)?.let {
            var finalPrice = 0.0
            it.home?.items?.forEach {
                finalPrice += (it.boughtQuantity * it.eachQtyValue)
            }
            _finalPrice.postValue(finalPrice)
        }
    }

    fun getPointsMap() = pointsMap
}
