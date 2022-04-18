package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class CashOrderResponse  (
    @SerializedName("IsSuccess")
    val IsSuccess: Int,
    @SerializedName("Message")
    val Message: String,
    @SerializedName("ValidationErrors")
    val ValidationErrors: String,
    @SerializedName("Data")
    val Data: CashOrderData
)

data class CashOrderData (
    @SerializedName("InvoiceId")
    val InvoiceId: Int,
    @SerializedName("IsDirectPayment")
    val IsDirectPayment: String,
    @SerializedName("PaymentURL")
    val PaymentURL: String,
    @SerializedName("CustomerReference")
    val CustomerReference: String,
    @SerializedName("UserDefinedField")
    val UserDefinedField: String,
    @SerializedName("RecurringId")
    val RecurringId: String
)
