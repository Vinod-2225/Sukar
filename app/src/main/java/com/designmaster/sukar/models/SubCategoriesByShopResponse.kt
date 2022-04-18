package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class SubCategoriesByShopResponse (

    @SerializedName("output")
    val output: SubCategoriesByShop

)


data class SubCategoriesByShop (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: List<SubCategoriesByShopData>
)

data class SubCategoriesByShopData (
    @SerializedName("id")
    val id: String,
    @SerializedName("shop_id")
    val shopId: String,
    @SerializedName("title_en")
    val titleEn: String,
    @SerializedName("title_ar")
    val titleAr: String,
    @SerializedName("ordering")
    val ordering: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("created_date")
    val createdDate: String
)