package com.example.pullrequest.endpoint

import com.example.pullrequest.GitHubConstants
import com.example.pullrequest.dtos.ClosedPullRequest
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

    @GET(GitHubConstants.REPO_END_POINT)
    fun fetchClosedPullRequest(@Query(GitHubConstants.REQUEST_STATE_PARAM) state: String): retrofit2.Call<List<ClosedPullRequest>>
}