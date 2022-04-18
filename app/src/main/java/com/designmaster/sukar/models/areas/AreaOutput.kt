package com.rcd.bato.models.ourServices

import android.os.Parcel
import android.os.Parcelable
import com.designmaster.sukar.models.GovernoratesInfo
import com.designmaster.sukar.models.areas.AreaInfo
import com.designmaster.sukar.models.governorates.GovernorateInfo
import com.google.gson.annotations.SerializedName

class AreaOutput {

    @SerializedName("success")
    val success: Int = 0
    @SerializedName("info")
    val info: List<AreaInfo>? = null


}