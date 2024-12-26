package com.bruceenterprises.githubapichallenge.data.repository.RepositoriesList

import com.bruceenterprises.githubapichallenge.data.remote.api.GithubApi
import com.bruceenterprises.githubapichallenge.data.remote.mapper.toDomain
import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.domain.repository.GithubRepositoriesRepository
import javax.inject.Inject

class GithubRepositoriesRepositoryImpl @Inject constructor(
    private val api: GithubApi,
) : GithubRepositoriesRepository {

    override suspend fun getRepositories( page: Int, perPage: Int): List<Repository> {
        val response = api.getJavaRepositories(page = page, perPage = perPage)
        return response.items.map { it.toDomain() }
    }
}
