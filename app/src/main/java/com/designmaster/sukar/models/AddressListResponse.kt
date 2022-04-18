package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class AddressListResponse(
    @SerializedName("output")
    val output: AddressListOutput
)

data class AddressListOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: List<AddressInfo>
)

data class AddressInfo (
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
    @SerializedName("areaname")
    val areaname: String,
    @SerializedName("governatesname")
    val governatesname: String
)