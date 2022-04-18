package com.designmaster.sukar.models.governorates

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class GovernorateInfo () : Parcelable {

    @SerializedName("id")
    var id: String? = null
    @SerializedName("title_en")
    var titleEn: String? = null
    @SerializedName("title_ar")
    var titleAr: String? = null
    @SerializedName("ordering")
    var ordering: String? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("created_date")
    var createdDate: String? = null

    constructor(parcel: Parcel) : this() {

        id = parcel.readString()
        titleEn = parcel.readString()
        titleAr = parcel.readString()
        ordering = parcel.readString()
        status = parcel.readString()
        createdDate = parcel.readString()

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeString(id)
        parcel.writeString(titleEn)
        parcel.writeString(titleAr)
        parcel.writeString(ordering)
        parcel.writeString(status)
        parcel.writeString(createdDate)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GovernorateInfo> {
        override fun createFromParcel(parcel: Parcel): GovernorateInfo {
            return GovernorateInfo(parcel)
        }

        override fun newArray(size: Int): Array<GovernorateInfo?> {
            return arrayOfNulls(size)
        }
    }
}