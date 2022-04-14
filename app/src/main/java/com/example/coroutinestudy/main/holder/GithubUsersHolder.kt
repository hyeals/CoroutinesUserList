package com.example.coroutinestudy.main.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_github_users.view.*

class GithubUsersHolder(view: View): RecyclerView.ViewHolder(view) {
    val id = view.item_id
    val nodeId = view.item_node_id
    val reposUrl = view.item_repos_url
}