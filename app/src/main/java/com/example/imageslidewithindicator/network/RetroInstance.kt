package com.example.imageslidewithindicator.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {
    var apiService: ApiService? = null
    fun getRetroInstance(): ApiService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        apiService = Retrofit.Builder()
            .baseUrl("https://howtodoandroid.com/apis/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(logging).build())
            .build().create(ApiService::class.java)

        return apiService!!
    }
}