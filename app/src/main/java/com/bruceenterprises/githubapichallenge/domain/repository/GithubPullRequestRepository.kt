package com.bruceenterprises.githubapichallenge.domain.repository

import com.bruceenterprises.githubapichallenge.domain.models.PullRequest

interface GithubPullRequestRepository {
    suspend fun getPullRequest(owner: String, repo: String): List<PullRequest>
}
