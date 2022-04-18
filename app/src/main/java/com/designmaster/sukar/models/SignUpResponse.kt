package com.designmaster.sukar.models


import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("output") val output : Output
)
data class Output (

    @SerializedName("success") val success : Int,
//    @SerializedName("data") val data : Int,
    @SerializedName("data") val data : ArrayList<UserData>,
    @SerializedName("message") val message : String
)


data class UserData (

//    @SerializedName("id") val id : Int,
//    @SerializedName("shop_id") val shop_id : Int,
//    @SerializedName("title_en") val title_en : String,
//    @SerializedName("title_ar") val title_ar : String,
//    @SerializedName("ordering") val ordering : Int,
//    @SerializedName("image") val image : String,
//    @SerializedName("status") val status : Int,
//    @SerializedName("created_date") val created_date : String,

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

