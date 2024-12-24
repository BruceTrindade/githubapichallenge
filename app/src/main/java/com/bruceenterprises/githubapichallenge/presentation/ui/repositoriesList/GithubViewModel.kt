package com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.domain.usecase.GetJavaRepositoriesUseCase
import com.bruceenterprises.githubapichallenge.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(private val getJavaRepositoriesUseCase: GetJavaRepositoriesUseCase) :
    ViewModel() {

    private val _repositories = MutableStateFlow<ResultState<List<Repository>>>(ResultState.Loading)
    val repositories: StateFlow<ResultState<List<Repository>>> get() = _repositories.asStateFlow()

    fun fetchRepositories() {
        viewModelScope.launch {
            _repositories.value = ResultState.Loading

            _repositories.value = try {
                val repos = getJavaRepositoriesUseCase()
                ResultState.Success(repos)
            } catch (e: Exception) {
                ResultState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}
