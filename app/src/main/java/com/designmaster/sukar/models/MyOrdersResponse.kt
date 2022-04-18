package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class MyOrdersResponse (
    @SerializedName("output")
    val output: MyOrdersOutput
)
data class MyOrdersOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: List<MyOrdersInfo>
)

data class MyOrdersInfo (
    @SerializedName("payment_id")
    val paymentId: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("OrderID")
    val OrderID: String,
    @SerializedName("order_status")
    val orderStatus: String
)
