package com.bruceenterprises.githubapichallenge.data.remote.api

import com.bruceenterprises.githubapichallenge.data.remote.dto.GithubResponse
import com.bruceenterprises.githubapichallenge.data.remote.dto.PullRequestDto
import com.bruceenterprises.githubapichallenge.data.remote.dto.PullRequestResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories")
    suspend fun getJavaRepositories(
        @Query("q") query: String = "language:java",
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc",
    ): GithubResponse

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<PullRequestDto>

}
