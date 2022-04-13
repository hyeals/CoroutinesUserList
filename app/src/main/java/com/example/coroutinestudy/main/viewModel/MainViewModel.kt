package com.example.coroutinestudy.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coroutinestudy.model.GithubUsersModel
import com.example.coroutinestudy.repository.GithubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel() {
    private val _githubUsers = MutableLiveData<GithubUsersModel>()
    val githubUsers = _githubUsers

    fun requestGithubUsers(){
        CoroutineScope(Dispatchers.IO).launch {
            GithubRepository().getUsers()?.let { res ->
                if(res.isSuccessful){
                    res.body()?.let {
                        _githubUsers.postValue(it)
                    }
                }
            }
        }
    }

}