package com.bruceenterprises.githubapichallenge.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import com.bruceenterprises.githubapichallenge.domain.repository.GithubPullRequestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPullRequestUseCase @Inject constructor(
    private val repository: GithubPullRequestRepository,
) {
    suspend operator fun invoke(owner: String, repo: String): Flow<PagingData<PullRequest>> {
        return repository.getPullRequest(owner, repo)
    }
}
