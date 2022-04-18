package com.example.coroutinestudy.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinestudy.model.GithubUserModel
import com.example.coroutinestudy.repository.GithubRepository
import com.example.coroutinestudy.repository.GithubRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val githubRepository: GithubRepository): ViewModel() {

    private val _githubUsers = MutableStateFlow<List<GithubUserModel>>(emptyList())
    val githubUsers: StateFlow<List<GithubUserModel>> get() = _githubUsers

    private val _requestGithubUsersSuccess = MutableStateFlow<Boolean>(false)
    val requestGithubUsersSuccess: StateFlow<Boolean> get() = _requestGithubUsersSuccess

    fun requestGithubUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            githubRepository.getUsers()?.let { res ->
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