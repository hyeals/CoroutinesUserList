package com.example.coroutinestudy.model

import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {
    @GET("/user")
    suspend fun getUsers(): Response<List<GithubUserModel>>
}