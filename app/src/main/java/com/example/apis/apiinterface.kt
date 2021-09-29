package com.example.apis


import com.example.dat.Data
import com.example.dat.RestaurantListData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("restaurants")
    fun getData(): Call<Data>
}