package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

class CommentListResponse(
    @SerializedName("output")
    val output: CommentListOutput
)

data class CommentListOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: List<CommentListInfo>
)

data class CommentListInfo (
    @SerializedName("id")
    val id: String,
    @SerializedName("feed_id")
    val feedId: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("date")
    val date: String
)