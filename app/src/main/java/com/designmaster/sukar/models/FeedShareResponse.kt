package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class FeedShareResponse(
    @SerializedName("output")
    val output: FeedShareOutput
)

data class FeedShareOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("total_shares")
    val totalShares: Int
)