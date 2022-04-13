package com.example.coroutinestudy.repository

import com.example.coroutinestudy.model.RetrofitClient

class GithubRepository {
    private val retrofitClient = RetrofitClient.getService()

    suspend fun getUsers() = retrofitClient.getUsers()
}