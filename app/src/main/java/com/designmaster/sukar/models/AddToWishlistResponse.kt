package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class AddToWishlistResponse  (
    @SerializedName("output")
    val output: AddToWishlistOutput
)

data class AddToWishlistOutput  (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: Int,
    @SerializedName("message")
    val message: String
)