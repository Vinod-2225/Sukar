package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class BannersByShopResponse (

    @SerializedName("output")
    val output: BannersByShopOutput

)

data class BannersByShopOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: List<BannersByShopInfo>,
    @SerializedName("logo_image")
    val logoImage: List<LogoImage>,
    @SerializedName("featured_image")
    val featuredImage: List<FeaturedImage>
)

data class BannersByShopInfo (
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
    @SerializedName("status")
    val status: String,
    @SerializedName("created_date")
    val createdDate: String
)

data class LogoImage (
    @SerializedName("id")
    val id: String,
    @SerializedName("banner_id")
    val bannerId: String,
    @SerializedName("shop_id")
    val shopId: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("created_date")
    val createdDate: String
)

data class FeaturedImage (
    @SerializedName("id")
    val id: String,
    @SerializedName("banner_id")
    val bannerId: String,
    @SerializedName("shop_id")
    val shopId: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("created_date")
    val createdDate: String
)