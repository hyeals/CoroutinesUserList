package com.example.coroutinestudy.repository

import com.example.coroutinestudy.model.GithubUserModel
import retrofit2.Response
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(private val remoteDataSource: GithubRemoteDataSource): GithubRepository {

    override suspend fun getUsers(): Response<List<GithubUserModel>> {
        return remoteDataSource.getUsers()
    }

}