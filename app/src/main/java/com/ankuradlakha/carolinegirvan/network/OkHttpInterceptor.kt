package com.ankuradlakha.carolinegirvan.network

import okhttp3.Interceptor
import okhttp3.Response

class OkHttpInterceptor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.header("Device-Type", "Android")
        builder.header("Accept", "application/vnd.vimeo.*+json;version=3.4")
        builder.header("Authorization", "bearer 51144491039787a1e095d43fd387422f")

        return chain.proceed(builder.build())
    }
}