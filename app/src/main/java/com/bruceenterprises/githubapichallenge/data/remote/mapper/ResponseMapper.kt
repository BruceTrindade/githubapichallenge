package com.bruceenterprises.githubapichallenge.data.remote.mapper

import com.bruceenterprises.githubapichallenge.data.remote.dto.RepositoryDto
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
