package com.example.apis

import com.example.apis.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
     companion object{
    fun getMyApis(url: String): ApiInterface {
        val retrofit =Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apis:ApiInterface=retrofit.create(ApiInterface::class.java)
        return apis
    }
}}