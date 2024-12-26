package com.bruceenterprises.githubapichallenge.domain.repository

import com.bruceenterprises.githubapichallenge.domain.models.Repository

interface GithubRepositoriesRepository {
    suspend fun getRepositories(page: Int, perPage: Int): List<Repository>
}
