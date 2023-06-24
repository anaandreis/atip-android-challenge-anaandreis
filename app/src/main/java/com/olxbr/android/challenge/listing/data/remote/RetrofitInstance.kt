package com.olxbr.android.challenge.listing.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://d30jh267s60dz7.cloudfront.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: AdsApi by lazy {
        retrofit.create(AdsApi::class.java)
    }
}