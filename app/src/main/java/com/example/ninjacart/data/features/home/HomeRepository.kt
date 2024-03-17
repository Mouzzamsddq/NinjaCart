package com.example.ninjacart.data.features.home

import com.example.ninjacart.data.features.home.source.HomeRemoteDataSource
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
) {

    suspend fun getHomePageData() = homeRemoteDataSource.getHomePageData()
}
