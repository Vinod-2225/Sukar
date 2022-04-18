package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class WishlistProductsByCategoryResponse  (
    @SerializedName("output")
    val output: WishlistProductsByCategoryOutput
)

data class WishlistProductsByCategoryOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: List<WishlistProductsByCategoryInfo>
)

data class WishlistProductsByCategoryInfo (
    @SerializedName("id")
    val id: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("shop_id")
    val shopId: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("shop_name")
    val shopName: String,
    @SerializedName("tilte_en")
    val tilteEn: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("flavour")
    val flavour: Any,
    @SerializedName("size")
    val size: Any
)