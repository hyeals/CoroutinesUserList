package com.example.coroutinestudy.model

import com.google.gson.annotations.SerializedName

data class GithubUserModel(
    @SerializedName("login")
    var login: String = "",
    @SerializedName("node_id")
    var nodeId: String = "",
    @SerializedName("repos_url")
    var reposUrl: String = ""
)
