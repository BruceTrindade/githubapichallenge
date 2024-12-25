package com.bruceenterprises.githubapichallenge.core.utils.mock

import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import com.bruceenterprises.githubapichallenge.domain.repository.GithubPullRequestRepository

class FakeGithubPullRequestRepository : GithubPullRequestRepository {
    override suspend fun getPullRequest(owner: String, repo: String): PagingSource<Int, PullRequest> {
        return listOf(
            PullRequest(
                id = 1L,
                title = "pr title",
                body = "pr description",
                htmlUrl = "https://github.com/example/repo/pull/1",
                createdAt = "2024-12-01T10:00:00Z",
                updatedAt = "2024-12-05T15:30:00Z",
                authorName = "pr owner",
                authorAvatarUrl = "https://avatar.url/1.png",
            ),
            PullRequest(
                id = 2L,
                title = "pr title",
                body = "pr description",
                htmlUrl = "https://github.com/example/repo/pull/1",
                createdAt = "2024-12-01T10:00:00Z",
                updatedAt = "2024-12-05T15:30:00Z",
                authorName = "pr owner",
                authorAvatarUrl = "https://avatar.url/1.png",
            ),
            PullRequest(
                id = 3L,
                title = "pr title",
                body = "pr description",
                htmlUrl = "https://github.com/example/repo/pull/1",
                createdAt = "2024-12-01T10:00:00Z",
                updatedAt = "2024-12-05T15:30:00Z",
                authorName = "pr owner",
                authorAvatarUrl = "https://avatar.url/1.png",
            ),
            PullRequest(
                id = 4L,
                title = "pr title",
                body = "",
                htmlUrl = "https://github.com/example/repo/pull/1",
                createdAt = "2024-12-01T10:00:00Z",
                updatedAt = "2024-12-05T15:30:00Z",
                authorName = "pr owner",
                authorAvatarUrl = "https://avatar.url/1.png",
            )
        )
    }
}

class FakeErrorGithubPullRequestRepository : GithubPullRequestRepository {
    override suspend fun getPullRequest(owner: String, repo: String): PagingSource<Int, PullRequest> {
        throw Exception("Erro na api")
    }
}
