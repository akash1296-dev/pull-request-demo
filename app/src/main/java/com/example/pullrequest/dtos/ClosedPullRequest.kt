package com.example.pullrequest.dtos

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ClosedPullRequest(
    @SerializedName("user") val gitUser: GitUser?,
    @SerializedName("title") val pullRequestTitle: String? = "",
    @SerializedName("created_at") val pullRequestCreateDate: String? = "",
    @SerializedName("closed_at") val pullRequestClosedDate: String? = "",
)
