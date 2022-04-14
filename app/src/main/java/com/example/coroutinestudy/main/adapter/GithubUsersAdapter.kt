package com.example.coroutinestudy.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinestudy.R
import com.example.coroutinestudy.main.holder.GithubUsersHolder
import com.example.coroutinestudy.model.GithubUserModel

class GithubUsersAdapter(): RecyclerView.Adapter<GithubUsersHolder>(){
    var data = mutableListOf<GithubUserModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdateDatas(data: List<GithubUserModel>){
        this.data = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUsersHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_github_users, parent, false)

        return GithubUsersHolder(view)
    }

    override fun onBindViewHolder(holder: GithubUsersHolder, position: Int) {
        holder.run {
            id.text = data[position].login
            nodeId.text = data[position].nodeId
            reposUrl.text = data[position].reposUrl
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}