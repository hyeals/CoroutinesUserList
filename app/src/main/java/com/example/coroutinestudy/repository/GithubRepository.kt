package com.example.coroutinestudy.repository

import com.example.coroutinestudy.model.GithubUserModel

interface GithubRepository {

    suspend fun getUsers(): List<GithubUserModel>
}
