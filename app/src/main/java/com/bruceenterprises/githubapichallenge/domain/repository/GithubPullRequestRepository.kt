package com.bruceenterprises.githubapichallenge.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import kotlinx.coroutines.flow.Flow

interface GithubPullRequestRepository {
    suspend fun getPullRequest(owner: String, repo: String): Flow<PagingData<PullRequest>>

}
