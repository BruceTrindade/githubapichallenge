package com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bruceenterprises.githubapichallenge.data.repository.RepositoriesList.RepositoriesPagingSource
import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.domain.usecase.GetJavaRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(private val getJavaRepositoriesUseCase: GetJavaRepositoriesUseCase) :
    ViewModel() {

    lateinit var repositories: Flow<PagingData<Repository>>

    private fun configPaging(): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { RepositoriesPagingSource(getJavaRepositoriesUseCase) }
        ).flow
    }

    fun fetchRepositories() {
        repositories = configPaging().cachedIn(viewModelScope)
    }
}