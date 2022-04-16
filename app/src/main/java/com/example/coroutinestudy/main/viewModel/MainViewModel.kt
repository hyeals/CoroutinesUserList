package com.example.coroutinestudy.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinestudy.model.GithubUserModel
import com.example.coroutinestudy.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel() {
    private val _githubUsers = MutableStateFlow<List<GithubUserModel>>(emptyList())
    val githubUsers: StateFlow<List<GithubUserModel>> get() = _githubUsers

    private val _requestGithubUsersSuccess = MutableStateFlow<Boolean>(false)
    val requestGithubUsersSuccess: StateFlow<Boolean> get() = _requestGithubUsersSuccess

    fun requestGithubUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            GithubRepository().getUsers()?.let { res ->
                if(res.isSuccessful){
                    res.body()?.let {
                        _githubUsers.value = it
                        _requestGithubUsersSuccess.value = true
                    }
                }else{
                    _requestGithubUsersSuccess.value = false
                }
            }
            // TODO: exception handling
        }
    }

}