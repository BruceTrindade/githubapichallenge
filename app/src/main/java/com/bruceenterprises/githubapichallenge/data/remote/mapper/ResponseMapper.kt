package com.bruceenterprises.githubapichallenge.data.remote.mapper

import com.bruceenterprises.githubapichallenge.data.remote.dto.PullRequestDto
import com.bruceenterprises.githubapichallenge.data.remote.dto.RepositoryDto
import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import com.bruceenterprises.githubapichallenge.domain.models.Repository

fun RepositoryDto.toDomain(): Repository {
    return Repository(
        id = id,
        name = name,
        description = description,
        stars = stars,
        ownerName = owner.login,
        ownerAvatarUrl = owner.avatarUrl,
        forksCount = forksCount,
    )
}

fun PullRequestDto.toDomain(): PullRequest {
    return PullRequest(
        id = id,
        title = title,
        body = body,
        htmlUrl = htmlUrl,
        createdAt = createdAt,
        updatedAt = updatedAt,
        authorName = user.login,
        authorAvatarUrl = user.avatarUrl
    )
}