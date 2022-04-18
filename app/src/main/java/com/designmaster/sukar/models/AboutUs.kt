package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class AboutUs(
    @SerializedName("output") val output : AboutOutput
)
data class AboutOutput (

    @SerializedName("success") val success : Int,
    @SerializedName("data") val data : List<AboutData>
)
data class AboutData (
    @SerializedName("id") val id : Int,
    @SerializedName("title_en") val title_en : String,
    @SerializedName("title_ar") val title_ar : String,
    @SerializedName("image") val image : String,
    @SerializedName("description_en") val description_en : String,
    @SerializedName("description_ar") val description_ar : String,
    @SerializedName("status") val status : Int,
    @SerializedName("created_date") val created_date : String
)