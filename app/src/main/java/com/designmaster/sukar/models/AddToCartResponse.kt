package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class AddToCartResponse(
    @SerializedName("output")
    val output: AddToCartInfo
)

data class AddToCartInfo (
    @SerializedName("success")
    val success: Int,
    @SerializedName("sub_total")
    val subTotal: Int,
    @SerializedName("info")
    val info: Int,
    @SerializedName("message")
    val message: String
)
