package com.example.coroutinestudy.model

import com.google.gson.annotations.SerializedName

data class GithubRepositoryModel(
    @SerializedName("login")
    val login: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("repos_url")
    val reposUrl: String
)
