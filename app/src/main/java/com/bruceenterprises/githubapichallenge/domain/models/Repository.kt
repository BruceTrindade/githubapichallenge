package com.bruceenterprises.githubapichallenge.domain.models

data class Repository(
    val id: Long,
    val name: String,
    val description: String?,
    val stars: Int,
    val ownerName: String,
    val ownerAvatarUrl: String,
    val forksCount: Int
)
