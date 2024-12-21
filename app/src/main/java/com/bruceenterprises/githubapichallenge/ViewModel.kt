package com.bruceenterprises.githubapichallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    private val _repositories = MutableStateFlow<List<Repository>>(emptyList())
    val repositories: StateFlow<List<Repository>> = _repositories

    init {
        fetchRepositories()
    }

    private fun fetchRepositories() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getJavaRepositories()
                _repositories.value = response.items
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
