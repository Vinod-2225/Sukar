package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class KnetOrderResponse (
    @SerializedName("IsSuccess")
    val IsSuccess: Boolean,
    @SerializedName("Message")
    val Message: String,
    @SerializedName("ValidationErrors")
    val ValidationErrors: Any,
    @SerializedName("Data")
    val Data: KnetOrderData
)

data class KnetOrderData (
    @SerializedName("InvoiceId")
    val InvoiceId: Int,
    @SerializedName("IsDirectPayment")
    val IsDirectPayment: Boolean,
    @SerializedName("PaymentURL")
    val PaymentURL: String,
    @SerializedName("CustomerReference")
    val CustomerReference: String,
    @SerializedName("UserDefinedField")
    val UserDefinedField: String,
    @SerializedName("RecurringId")
    val RecurringId: String
)