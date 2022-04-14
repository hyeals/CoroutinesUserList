package com.example.coroutinestudy.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinestudy.model.GithubUserModel
import com.example.coroutinestudy.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel() {
    private val _githubUsers = MutableLiveData<List<GithubUserModel>>()
    val githubUsers: LiveData<List<GithubUserModel>> get() = _githubUsers

    private val _requestGithubUsersSuccess = MutableLiveData<Boolean>()
    val requestGithubUsersSuccess: LiveData<Boolean> get() = _requestGithubUsersSuccess

    fun requestGithubUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            GithubRepository().getUsers()?.let { res ->
                if(res.isSuccessful){
                    res.body()?.let {
                        _githubUsers.postValue(it)
                        _requestGithubUsersSuccess.postValue(true)
                    }
                }else{
                    _requestGithubUsersSuccess.postValue(false)
                }
            }
            // TODO: exception handling
        }
    }

}