package com.example.coroutinestudy.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutinestudy.R
import com.example.coroutinestudy.databinding.ActivityMainBinding
import com.example.coroutinestudy.main.adapter.GithubUsersAdapter
import com.example.coroutinestudy.main.viewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        val adapter = GithubUsersAdapter()
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(applicationContext)


        viewModel.requestGithubUsers()

        // observing
        viewModel.githubUsers.observe(this){
            if(it != null){
                adapter.setUpdateDatas(viewModel.githubUsers.value!!)
            }else{
                Log.d("this!!!", "this null value")
            }
        }
        viewModel.requestGithubUsersSuccess.observe(this){
            if(it){
                binding.progressBar.visibility = View.GONE
            }else{
                binding.progressBar.visibility = View.VISIBLE
            }
        }

    }
}