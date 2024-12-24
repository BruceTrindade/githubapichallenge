package com.bruceenterprises.githubapichallenge.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GithubResponse(
    @SerializedName("items") val items: List<RepositoryDto>
)

data class RepositoryDto(
    val id: Long,
    val name: String,
    val description: String?,
    @SerializedName("stargazers_count") val stars: Int,
    val owner: OwnerDTO,
    @SerializedName("forks_count") val forksCount: Int,
)

data class OwnerDTO(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)
