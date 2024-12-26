package com.bruceenterprises.githubapichallenge.domain.usecase

import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import com.bruceenterprises.githubapichallenge.domain.repository.GithubPullRequestRepository
import javax.inject.Inject

class GetPullRequestUseCase @Inject constructor(
    private val repository: GithubPullRequestRepository,
) {
    suspend operator fun invoke(owner: String, repo: String, page: Int, perPage: Int): List<PullRequest> {
        return repository.getPullRequest(owner = owner, repo = repo, page = page, perPage = perPage)
    }
}
