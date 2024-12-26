package com.bruceenterprises.githubapichallenge.presentation.ui.pullrequestdetails

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import com.bruceenterprises.githubapichallenge.domain.usecase.GetPullRequestUseCase

class PullRequestsPagingSource(
    private val useCase: GetPullRequestUseCase,
    private val owner: String,
    private val repo: String
) : PagingSource<Int, PullRequest>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PullRequest> {
        val page = params.key ?: 1
        val perPage = params.loadSize

        return try {
            val response = useCase(owner, repo, page, perPage)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.size < perPage) null else page + 1
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
