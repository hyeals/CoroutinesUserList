package com.example.coroutinestudy.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getService() = getClient().create(RetrofitApi::class.java)

    private fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getUrl())
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getUrl(): String {
        return "https://api.github.com"
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }
}
