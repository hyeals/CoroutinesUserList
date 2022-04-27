package com.example.coroutinestudy.repository

import com.example.coroutinestudy.model.GithubUserModel
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(private val remoteDataSource: GithubRemoteDataSource) : GithubRepository {

    override suspend fun getUsers(): List<GithubUserModel> {
        return remoteDataSource.getUsers()
    }
}
