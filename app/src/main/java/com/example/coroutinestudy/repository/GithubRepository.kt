package com.example.coroutinestudy.repository

import com.example.coroutinestudy.model.GithubUserModel
import retrofit2.Response

interface GithubRepository {

    suspend fun getUsers(): Response<List<GithubUserModel>>
}