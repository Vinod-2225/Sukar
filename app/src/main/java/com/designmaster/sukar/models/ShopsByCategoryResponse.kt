package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class ShopsByCategoryResponse(
    @SerializedName("output")
    val output: ShopsByCategory
)

data class ShopsByCategory (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: List<ShopsData>

)

data class ShopsData (
    @SerializedName("id")
    val id: String,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("title_en")
    val titleEn: String,
    @SerializedName("title_ar")
    val titleAr: String,
    @SerializedName("ordering")
    val ordering: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("store_status")
    val storeStatus: String,
    @SerializedName("contact")
    val contact: String,
    @SerializedName("contact_email")
    val contactEmail: String,
    @SerializedName("contact_phone")
    val contactPhone: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("login_user")
    val loginUser: String,
    @SerializedName("login_password")
    val loginPassword: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("created_date")
    val createdDate: String
)
