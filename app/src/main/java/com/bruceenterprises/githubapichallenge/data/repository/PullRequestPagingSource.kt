package com.bruceenterprises.githubapichallenge.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bruceenterprises.githubapichallenge.data.remote.api.GithubApi
import com.bruceenterprises.githubapichallenge.data.remote.mapper.toDomain
import com.bruceenterprises.githubapichallenge.domain.models.PullRequest

class PullRequestsPagingSource(
    private val api: GithubApi,
    private val owner: String,
    private val repo: String
) : PagingSource<Int, PullRequest>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PullRequest> {
        val page = params.key ?: 1
        val perPage = params.loadSize

        return try {
            val response = api.getPullRequests(owner, repo, page, perPage) // Busca os dados
            LoadResult.Page(
                data = response.map { it.toDomain() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PullRequest>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
