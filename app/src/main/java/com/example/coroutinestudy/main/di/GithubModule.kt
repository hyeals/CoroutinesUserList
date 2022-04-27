package com.example.coroutinestudy.main.di

import com.example.coroutinestudy.repository.GithubRemoteDataSource
import com.example.coroutinestudy.repository.GithubRemoteDataSourceImpl
import com.example.coroutinestudy.repository.GithubRepository
import com.example.coroutinestudy.repository.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GithubModule {
    @Binds
    abstract fun bindGithubRepository(githubRepositoryImpl: GithubRepositoryImpl): GithubRepository
    @Binds
    abstract fun bindRemoteDataSource(githubRemoteDataSourceImpl: GithubRemoteDataSourceImpl): GithubRemoteDataSource
}
