package com.ankuradlakha.carolinegirvan.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("{video_id}/config")
    fun getVimeoUrlResponse(@Path("video_id") id: String): Call<JsonObject>

    @GET("me/videos")
    fun getAllVideos(): Call<JsonObject>
}