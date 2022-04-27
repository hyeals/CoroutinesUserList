package com.example.coroutinestudy.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinestudy.model.GithubUserModel
import com.example.coroutinestudy.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val githubRepository: GithubRepository) : ViewModel() {

    private val _githubUsers = MutableStateFlow<List<GithubUserModel>>(emptyList())
    val githubUsers: StateFlow<List<GithubUserModel>> get() = _githubUsers

    private val _requestGithubUsersSuccess = MutableStateFlow<Boolean>(false)
    val requestGithubUsersSuccess: StateFlow<Boolean> get() = _requestGithubUsersSuccess

    fun requestGithubUsers() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            githubRepository.getUsers().let { res ->
                _githubUsers.value = res
                _requestGithubUsersSuccess.value = true
            }
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        when (exception) {
            is HttpException -> _requestGithubUsersSuccess.value = false
        }
    }
}
