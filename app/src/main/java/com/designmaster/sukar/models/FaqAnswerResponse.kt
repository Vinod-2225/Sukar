package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class FaqAnswerResponse(
    @SerializedName("output")
    val output: FaqAnswerOutput
)

data class FaqAnswerOutput (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: List<FaqAnswerInfo>
)

data class FaqAnswerInfo (
    @SerializedName("question_en")
    val questionEn: String,
    @SerializedName("answer_en")
    val answerEn: String,
    @SerializedName("question_ar")
    val questionAr: String,
    @SerializedName("answer_ar")
    val answerAr: String
)