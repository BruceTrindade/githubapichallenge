package com.bruceenterprises.githubapichallenge.data.repository.PullRequest

import com.bruceenterprises.githubapichallenge.data.remote.api.GithubApi
import com.bruceenterprises.githubapichallenge.data.remote.mapper.toDomain
import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import com.bruceenterprises.githubapichallenge.domain.repository.GithubPullRequestRepository
import javax.inject.Inject

class GithubPullRequestRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : GithubPullRequestRepository {

    override suspend fun getPullRequest(owner: String, repo: String, page: Int, perPage: Int): List<PullRequest> {
       return api.getPullRequests(owner = owner, repo = repo, page = page, perPage = perPage).map { it.toDomain() }
    }

}
