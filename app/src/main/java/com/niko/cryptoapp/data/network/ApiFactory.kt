package com.niko.cryptoapp.data.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = "https://min-api.cryptocompare.com/data/"
    const val BASE_IMG_URL = "https:cryptocompare.com"
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
    val api_service = retrofit.create(ApiService::class.java)
}