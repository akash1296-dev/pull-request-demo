package com.example.pullrequest.endpoint

import com.example.pullrequest.dtos.ClosedPullRequest
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

    @GET("/repos/akash1296-dev/EmojiDemo/pulls")
    fun fetchClosedPullRequest(@Query("state") state: String): retrofit2.Call<List<ClosedPullRequest>>
}