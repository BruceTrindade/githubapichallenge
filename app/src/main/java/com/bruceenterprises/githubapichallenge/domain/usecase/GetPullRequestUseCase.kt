package com.bruceenterprises.githubapichallenge.domain.usecase

import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.domain.repository.GithubPullRequestRepository
import com.bruceenterprises.githubapichallenge.domain.repository.GithubRepositoriesRepository
import javax.inject.Inject

class GetPullRequestUseCase @Inject constructor(
    private val repository: GithubPullRequestRepository,
) {
    suspend operator fun invoke(owner: String, repo: String): List<PullRequest> {
        return repository.getPullRequest(owner, repo)
    }
}
