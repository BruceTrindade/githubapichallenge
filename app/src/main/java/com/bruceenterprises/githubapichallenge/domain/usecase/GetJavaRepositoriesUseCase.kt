package com.bruceenterprises.githubapichallenge.domain.usecase

import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.domain.repository.GithubRepositoriesRepository
import javax.inject.Inject

class GetJavaRepositoriesUseCase @Inject constructor(
    private val repository: GithubRepositoriesRepository,
) {
    suspend operator fun invoke(page: Int, perPage: Int): List<Repository> {
        return repository.getRepositories(page, perPage)
    }
}
