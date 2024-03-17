package com.example.ninjacart.data.features.home.response


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("eachQtyValue")
    val eachQtyValue: Int? = null,
    @SerializedName("multiple")
    val multiple: Int? = null,
    @SerializedName("name")
    val name: String? = null
)