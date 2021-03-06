package com.example.coroutinestudy.model

import retrofit2.http.GET

interface RetrofitApi {
    @GET("/users")
    suspend fun getUsers(): List<GithubUserModel>
}
