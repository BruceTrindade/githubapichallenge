package com.bruceenterprises.githubapichallenge.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PullRequestResponse(
    @SerializedName("items") val items: List<PullRequestDto>
)

data class PullRequestDto(
    val id: Long,
    val title: String,
    val body: String?,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    val user: UserDto,
)

data class UserDto(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
)
