package com.bruceenterprises.githubapichallenge.data.remote.api

import com.bruceenterprises.githubapichallenge.data.remote.dto.GithubResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories")
    suspend fun getJavaRepositories(
        @Query("q") query: String = "language:java",
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc",
    ): GithubResponse
}
