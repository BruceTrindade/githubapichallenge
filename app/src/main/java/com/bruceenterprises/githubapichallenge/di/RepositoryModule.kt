package com.bruceenterprises.githubapichallenge.di

import com.bruceenterprises.githubapichallenge.data.remote.api.GithubApi
import com.bruceenterprises.githubapichallenge.data.repository.GithubRepositoriesRepositoryImpl
import com.bruceenterprises.githubapichallenge.domain.repository.GithubRepositoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubRepositoriesRepository(
        githubApi: GithubApi
    ): GithubRepositoriesRepository {
        return GithubRepositoriesRepositoryImpl(githubApi)
    }
}
