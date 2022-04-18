package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class Bannersresponse(
    @SerializedName("output") val output : BannersOutput
)
data class BannersOutput (

    @SerializedName("success") val success : Int,
    @SerializedName("data") val data : ArrayList<BannersData>
)

data class BannersData (

    @SerializedName("id") val id : Int,
    @SerializedName("shop_id") val shop_id : Int,
    @SerializedName("title_en") val title_en : String,
    @SerializedName("title_ar") val title_ar : String,
    @SerializedName("ordering") val ordering : Int,
    @SerializedName("image") val image : String,
    @SerializedName("logo_image") val logo_image : String,
    @SerializedName("featured_image") val featured_image : String,
    @SerializedName("status") val status : Int,
    @SerializedName("created_date") val created_date : String
)