package com.bruceenterprises.githubapichallenge.di

import com.bruceenterprises.githubapichallenge.data.remote.api.GithubApi
import com.bruceenterprises.githubapichallenge.data.repository.PullRequest.GithubPullRequestRepositoryImpl
import com.bruceenterprises.githubapichallenge.data.repository.RepositoriesList.GithubRepositoriesRepositoryImpl
import com.bruceenterprises.githubapichallenge.domain.repository.GithubPullRequestRepository
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

    @Provides
    @Singleton
    fun providePullRequestRepository(
        githubApi: GithubApi
    ): GithubPullRequestRepository {
        return GithubPullRequestRepositoryImpl(githubApi)
    }
}
