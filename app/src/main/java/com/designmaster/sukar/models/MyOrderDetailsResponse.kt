package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class MyOrderDetailsResponse (
    @SerializedName("output")
    val output: MyOrderDetailsOutput
)

data class MyOrderDetailsOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("sub_total")
    val subTotal: String,
    @SerializedName("address")
    val address: List<MyOrderDetailsAddress>,
    @SerializedName("info")
    val info: List<MyOrderDetailsInfo>
)

data class MyOrderDetailsAddress (
    @SerializedName("id")
    val id: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("countrycode")
    val countrycode: String,
    @SerializedName("mobile_no")
    val mobileNo: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("area")
    val area: String,
    @SerializedName("governate")
    val governate: String,
    @SerializedName("building_no")
    val buildingNo: String,
    @SerializedName("block")
    val block: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("floor_no")
    val floorNo: String,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lan")
    val lan: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("area_name")
    val areaName: String
)

data class MyOrderDetailsInfo (
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("product_price")
    val productPrice: String,
    @SerializedName("total")
    val total: String,
    @SerializedName("delivery_charges")
    val deliveryCharges: String,
    @SerializedName("orderID")
    val orderID: String,
    @SerializedName("quantity")
    val quantity: String,
    @SerializedName("shop_name")
    val shopName: String,
    @SerializedName("flavours")
    val flavours: Any,
    @SerializedName("sizes")
    val sizes: Any
)