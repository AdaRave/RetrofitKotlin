package com.example.retrofit.retrofit

import com.example.retrofit.retrofit.Const.URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val retrofit by lazy {
        Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    val api: RetrofitInterface by lazy { retrofit.create(RetrofitInterface::class.java) }
}