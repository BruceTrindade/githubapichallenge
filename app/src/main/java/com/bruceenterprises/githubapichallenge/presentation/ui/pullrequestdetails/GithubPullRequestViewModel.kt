package com.bruceenterprises.githubapichallenge.presentation.ui.pullrequestdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.domain.usecase.GetJavaRepositoriesUseCase
import com.bruceenterprises.githubapichallenge.domain.usecase.GetPullRequestUseCase
import com.bruceenterprises.githubapichallenge.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubPullRequestViewModel @Inject constructor(private val getPullRequestUseCase: GetPullRequestUseCase) :
    ViewModel() {

    private val _repositories = MutableStateFlow<ResultState<List<PullRequest>>>(ResultState.Loading)
    val repositories: StateFlow<ResultState<List<PullRequest>>> get() = _repositories.asStateFlow()

    fun fetchPullRequest(owner: String, repo: String) {
        viewModelScope.launch {
            _repositories.value = ResultState.Loading

            _repositories.value = try {
                val repos = getPullRequestUseCase(owner, repo)
                ResultState.Success(repos)
            } catch (e: Exception) {
                ResultState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}
