package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("output")
    val output: CartData
)

data class CartData (
    @SerializedName("success")
    val success: Int,
    @SerializedName("sub_total")
    val subTotal: String,
    @SerializedName("info")
    val info: List<CartInfo>
)

data class CartInfo (
    @SerializedName("id")
    val id: String,
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("quantity")
    val quantity: String,
    @SerializedName("sub_total")
    val subTotal: String,
    @SerializedName("comments")
    val comments: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("flavour")
    val flavour: String,
    @SerializedName("size")
    val size: String
)
