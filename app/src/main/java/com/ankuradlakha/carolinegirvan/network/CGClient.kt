package com.ankuradlakha.carolinegirvan.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CGClient {
    fun getClient(): Retrofit {
        val client = OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }).addInterceptor(OkHttpInterceptor())
            .build()
        return Retrofit.Builder().baseUrl("https://player.vimeo.com/video/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}