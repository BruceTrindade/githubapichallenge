package com.bruceenterprises.githubapichallenge

import com.google.gson.annotations.SerializedName

data class GitHubResponse(
    @SerializedName("items") val items: List<Repository>
)

data class Repository(
    val id: Long,
    val name: String,
    val description: String?,
    @SerializedName("stargazers_count") val stars: Int,
    val owner: Owner,
    @SerializedName("forks_count") val forksCount: Int,
)

data class Owner(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)
