package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class UpdateProfileResponse(
    @SerializedName("output")
    val output: UpdateProfile
)

data class UpdateProfile (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: Int,
    @SerializedName("message")
    val message: String
)
