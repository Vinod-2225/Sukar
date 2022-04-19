package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

class FeedResponse(
    @SerializedName("output")
    val output: FeedOutput
)

data class FeedOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: List<FeedInfo>
)

data class FeedInfo (
    @SerializedName("id")
    val id: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("likes")
    val likes: String,
    @SerializedName("shares")
    val shares: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("comments")
    val comments: String,
    @SerializedName("profile_image")
    val profileImage: String
)