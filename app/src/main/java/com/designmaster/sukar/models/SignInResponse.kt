package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

class SignInResponse(
    @SerializedName("output")
    val output: SignInOutput
)

data class SignInOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("data")
    val dataField: List<SignInDataField>
)

data class SignInDataField (
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
    @SerializedName("image")
    val image: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("countrycode")
    val countrycode: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("device_token")
    val deviceToken: String,
    @SerializedName("device_type")
    val deviceType: String
)