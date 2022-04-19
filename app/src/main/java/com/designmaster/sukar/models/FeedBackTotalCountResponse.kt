package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

class FeedBackTotalCountResponse (
    @SerializedName("output")
    val output: FeedBackTotalCountOutput
)

data class FeedBackTotalCountOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("data")
    val dataField: List<FeedBackTotalCountDataField>
)

data class FeedBackTotalCountDataField (
    @SerializedName("total_posts")
    val totalPosts: String,
    @SerializedName("total_photos")
    val totalPhotos: String
)