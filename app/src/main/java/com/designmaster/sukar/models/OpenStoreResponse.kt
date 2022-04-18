package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

class OpenStoreResponse (
    @SerializedName("output")
    val output: OpenStoreOutput
)

data class OpenStoreOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: Int
)