package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

class FeedCommentResponse (
    @SerializedName("output")
    val output: FeedCommentOutput
)


data class FeedCommentOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: Int
)