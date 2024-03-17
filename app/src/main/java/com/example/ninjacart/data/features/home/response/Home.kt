package com.example.ninjacart.data.features.home.response

import com.google.gson.annotations.SerializedName

data class Home(
    @SerializedName("items")
    val items: List<Item?>? = null,
    @SerializedName("max")
    val max: Int? = null,
    @SerializedName("min")
    val min: Int? = null,
    @SerializedName("points")
    val points: List<Int?>? = null,
)
