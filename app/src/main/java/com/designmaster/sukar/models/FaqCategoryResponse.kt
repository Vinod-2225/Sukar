package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class FaqCategoryResponse (
    @SerializedName("output")
    val output: FaqCategoryOutput
)

data class FaqCategoryOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("data")
    val dataField: List<FaqCategoryDataField>
)

data class FaqCategoryDataField (
    @SerializedName("id")
    val id: String,
    @SerializedName("title_en")
    val titleEn: String,
    @SerializedName("title_ar")
    val titleAr: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("created_date")
    val createdDate: String
)