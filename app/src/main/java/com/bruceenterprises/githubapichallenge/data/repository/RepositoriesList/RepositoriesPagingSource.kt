package com.bruceenterprises.githubapichallenge.data.repository.RepositoriesList

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.domain.usecase.GetJavaRepositoriesUseCase

class RepositoriesPagingSource(
    private val useCase: GetJavaRepositoriesUseCase,
) : PagingSource<Int, Repository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val page = params.key ?: 1
        val perPage = params.loadSize

        return try {
            val response = useCase.invoke(page = page, perPage = perPage)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.size < perPage) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
