package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class MyProfileResponse (

    @SerializedName("output")
    val output: MyProfileInfo
)

data class MyProfileInfo (

    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: List<Info>
)

data class Info (
    @SerializedName("id")
    val id: String,
    @SerializedName("fname")
    val fname: String,
    @SerializedName("lname")
    val lname: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("secretcode")
    val secretcode: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("countrycode")
    val countrycode: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("status")
    val status: String
)