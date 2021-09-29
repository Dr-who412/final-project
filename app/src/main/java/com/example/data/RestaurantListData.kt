package com.example.dat

data class RestaurantListData(
    val address: String,
    val id: Int,
    val image: String,
    val name: String,
    val products: List<ProductData>,
    val restaurant_lat: String,
    val restaurant_long: String
)