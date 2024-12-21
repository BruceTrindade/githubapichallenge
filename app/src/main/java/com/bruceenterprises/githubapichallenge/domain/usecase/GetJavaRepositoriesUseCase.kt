package com.bruceenterprises.githubapichallenge.domain.usecase

import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.domain.repository.GithubRepositoriesRepository

class GetJavaRepositoriesUseCase(
    private val repository: GithubRepositoriesRepository,
) {
    suspend operator fun invoke(): List<Repository> {
        return repository.getRepositories()
    }
}
