package com.example.ninjacart.data.features.home.response

data class Item(
    val eachQtyValue: Int,
    val multiple: Int,
    val name: String,
    var boughtQuantity: Int,
) {
    constructor() : this(0, 0, "", 0)
}

data class Point(
    val value: Double,
) {
    constructor() : this(0.0)
}

data class Home(
    val items: List<Item>,
    val max: Double,
    val min: Double,
    val points: List<Point>,
) {
    constructor() : this(emptyList(), 0.0, 0.0, emptyList())
}
