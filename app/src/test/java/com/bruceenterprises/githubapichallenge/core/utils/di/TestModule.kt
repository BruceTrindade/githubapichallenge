package com.bruceenterprises.githubapichallenge.core.utils.di

import com.bruceenterprises.githubapichallenge.core.utils.mock.FakeGithubPullRequestRepository
import com.bruceenterprises.githubapichallenge.core.utils.mock.FakeGithubRepositoriesRepository
import com.bruceenterprises.githubapichallenge.di.RepositoryModule
import com.bruceenterprises.githubapichallenge.domain.repository.GithubPullRequestRepository
import com.bruceenterprises.githubapichallenge.domain.repository.GithubRepositoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object TestModule {

    @Provides
    fun provideGithubRepositoriesRepository(): GithubRepositoriesRepository {
        return FakeGithubRepositoriesRepository()
    }

    @Provides
    fun provideGithubPullRequestRepository(): GithubPullRequestRepository {
        return FakeGithubPullRequestRepository()
    }

}
