package com.bruceenterprises.githubapichallenge.data.repository

import com.bruceenterprises.githubapichallenge.data.remote.api.GithubApi
import com.bruceenterprises.githubapichallenge.data.remote.mapper.toDomain
import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.domain.repository.GithubPullRequestRepository
import com.bruceenterprises.githubapichallenge.domain.repository.GithubRepositoriesRepository
import javax.inject.Inject

class GithubPullRequestRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : GithubPullRequestRepository {

    override suspend fun getPullRequest(owner: String, repo: String): List<PullRequest> {
        val response = api.getPullRequests(owner, repo)
        return response.map { it.toDomain() }
    }
}
