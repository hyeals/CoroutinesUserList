package com.example.coroutinestudy.main.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutinestudy.databinding.ActivityMainBinding
import com.example.coroutinestudy.main.adapter.GithubUsersAdapter
import com.example.coroutinestudy.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        val adapter = GithubUsersAdapter()
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(applicationContext)

        // call gitHubUsers
        viewModel.requestGithubUsers()

        // collect
        with(viewModel) {
            lifecycleScope.launch {
                githubUsers.onEach {
                    if(it.isNotEmpty()){
                        adapter.setUpdateDatas(viewModel.githubUsers.value)
                    }else{
                        Log.d("this!!!", "this null value")
                    }
                }.launchIn(this)
            }
//            githubUsers.asLiveData().observe(this@MainActivity) {
//                if (it != null) {
//                    adapter.setUpdateDatas(viewModel.githubUsers.value)
//                } else {
//                    Log.d("this!!!", "this null value")
//                }
//            }
        }
    }
}
