package com.bruceenterprises.githubapichallenge.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bruceenterprises.githubapichallenge.data.remote.api.GithubApi
import com.bruceenterprises.githubapichallenge.data.remote.mapper.toDomain
import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import com.bruceenterprises.githubapichallenge.domain.repository.GithubPullRequestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubPullRequestRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : GithubPullRequestRepository {

    override suspend fun getPullRequest(owner: String, repo: String): Flow<PagingData<PullRequest>> {
       return Pager(
            config = PagingConfig(
                pageSize = 30, // Tamanho da página
                enablePlaceholders = false // Desativar espaços reservados
            ),
            pagingSourceFactory = { PullRequestsPagingSource(api, owner, repo) }
        ).flow
    }

}
