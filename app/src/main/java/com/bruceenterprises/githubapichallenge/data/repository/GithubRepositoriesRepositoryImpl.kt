package com.bruceenterprises.githubapichallenge.data.repository

import com.bruceenterprises.githubapichallenge.data.remote.api.GithubApi
import com.bruceenterprises.githubapichallenge.data.remote.mapper.toDomain
import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.domain.repository.GithubRepositoriesRepository

class GithubRepositoriesRepositoryImpl(private val api: GithubApi) : GithubRepositoriesRepository {

    override suspend fun getRepositories(): List<Repository> {
        val response = api.getJavaRepositories()
        return response.items.map { it.toDomain() }
    }
}
