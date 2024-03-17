package com.example.ninjacart.data.features.home.response

data class Item(
    val eachQtyValue: Int,
    val multiple: Int,
    val name: String,
) {
    constructor() : this(0, 0, "")
}

data class Point(
    val value: Int,
) {
    constructor() : this(0)
}

data class Home(
    val items: List<Item>,
    val max: Int,
    val min: Int,
    val points: List<Point>,
) {
    constructor() : this(emptyList(), 0, 0, emptyList())
}
