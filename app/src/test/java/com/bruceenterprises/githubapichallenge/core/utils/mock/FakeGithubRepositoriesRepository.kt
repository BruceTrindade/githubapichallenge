package com.bruceenterprises.githubapichallenge.core.utils.mock

import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.domain.repository.GithubRepositoriesRepository

class FakeGithubRepositoriesRepository : GithubRepositoriesRepository {
    override suspend fun getRepositories(page: Int, perPage: Int): List<Repository> {
        val fakeRepositories = listOf(
            Repository(
                id = 1,
                name = "Repo 1",
                description = "Descrição teste 1",
                stars = 100,
                forksCount = 50,
                ownerName = "name 1",
                ownerAvatarUrl = "https://avatar.url/1.png",
            ),
            Repository(
                id = 2,
                name = "Repo 2",
                description = "Descrição teste 2",
                stars = 100,
                forksCount = 50,
                ownerName = "name 2",
                ownerAvatarUrl = "https://avatar.url/2.png",
            ),
        )
        return fakeRepositories
    }
}
