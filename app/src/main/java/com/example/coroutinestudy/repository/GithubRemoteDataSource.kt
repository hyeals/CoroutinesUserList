package com.example.coroutinestudy.repository

import com.example.coroutinestudy.model.GithubUserModel
import com.example.coroutinestudy.model.RetrofitClient
import javax.inject.Inject

interface GithubRemoteDataSource {

    /**
     * 유저 정보를 반환합니다.
     * @return [List<GithubUserModel>] 유저 리스트
     * */
    suspend fun getUsers(): List<GithubUserModel>
}

class GithubRemoteDataSourceImpl @Inject constructor() : GithubRemoteDataSource {
    companion object {
        private val retrofitClient = RetrofitClient.getService()
    }

    override suspend fun getUsers(): List<GithubUserModel> {
        return retrofitClient.getUsers()
    }
}
