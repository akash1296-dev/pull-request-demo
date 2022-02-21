package com.example.pullrequest.dtos

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GitUser(
    @SerializedName("login") val userName: String? = "",
    @SerializedName("avatar_url") val userAvatarUrl: String? = ""
)
