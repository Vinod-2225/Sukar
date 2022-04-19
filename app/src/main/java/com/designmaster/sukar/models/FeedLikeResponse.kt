package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

class FeedLikeResponse (
    @SerializedName("output")
    val output: FeedLikeOutput
)

data class FeedLikeOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("total_likes")
    val totalLikes: Int
)