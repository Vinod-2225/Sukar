package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class GovernoratesResponse (
    @SerializedName("output")
    val governoratesOutput: GovernoratesOutput
)

data class GovernoratesOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val governoratesInfo: List<GovernoratesInfo>
)

data class GovernoratesInfo (
    @SerializedName("id")
    val id: String,
    @SerializedName("title_en")
    val titleEn: String,
    @SerializedName("title_ar")
    val titleAr: String,
    @SerializedName("ordering")
    val ordering: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("created_date")
    val createdDate: String
)