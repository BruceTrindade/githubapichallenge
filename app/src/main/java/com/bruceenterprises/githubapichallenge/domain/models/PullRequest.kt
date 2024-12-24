package com.bruceenterprises.githubapichallenge.domain.models

data class PullRequest(
    val id: Long,
    val title: String,
    val body: String?,
    val htmlUrl: String,
    val createdAt: String,
    val updatedAt: String,
    val authorName: String,
    val authorAvatarUrl: String
)