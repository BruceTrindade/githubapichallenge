package com.bruceenterprises.githubapichallenge.presentation.ui.pullrequestdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import com.bruceenterprises.githubapichallenge.domain.usecase.GetPullRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GithubPullRequestViewModel @Inject constructor(
    private val getPullRequestUseCase: GetPullRequestUseCase
) : ViewModel() {

    lateinit var pullRequests: Flow<PagingData<PullRequest>>

    private fun configPaging(owner: String, repo: String): Flow<PagingData<PullRequest>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                PullRequestsPagingSource(
                    owner = owner,
                    repo = repo,
                    useCase = getPullRequestUseCase
                )
            }
        ).flow
    }


    fun fetchPullRequests(owner: String, repo: String) {
        pullRequests = configPaging(owner, repo)
            .cachedIn(viewModelScope)
    }
}
