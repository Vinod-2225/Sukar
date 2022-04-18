package com.designmaster.sukar.models.areas

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class AreaInfo  () : Parcelable  {

    @SerializedName("id")
    val id: String? = null
    @SerializedName("governates_id")
    val governatesId: String? = null
    @SerializedName("title_en")
    val titleEn: String? = null
    @SerializedName("title_ar")
    val titleAr: String? = null
    @SerializedName("delivery_charges")
    val deliveryCharges: String? = null
    @SerializedName("ordering")
    val ordering: String? = null
    @SerializedName("status")
    val status: String? = null
    @SerializedName("created_date")
    val createdDate: String? = null
    @SerializedName("ids")
    val ids: String? = null
    @SerializedName("title3")
    val title3: String? = null
    @SerializedName("title2")
    val title2: String? = null
    @SerializedName("name")
    val name: String? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AreaInfo> {
        override fun createFromParcel(parcel: Parcel): AreaInfo {
            return AreaInfo(parcel)
        }

        override fun newArray(size: Int): Array<AreaInfo?> {
            return arrayOfNulls(size)
        }
    }

}