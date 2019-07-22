package com.example.myfirstandroidapp.model

import android.os.Parcel
import android.os.Parcelable

data class APIResponse(
    val ADDR1: String?,
    val CASE_COUNT: Int,
    val CITY: String?,
    val CUST_CD: String?,
    val EMAIL_ADDR: String?,
    val FNAME: String?,
    val HOME_PHONE: String?,
    val LNAME: String?,
    val ORDER_COUNT: Int,
    val ST_CD: String?,
    val ZIP_CD: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ADDR1)
        parcel.writeInt(CASE_COUNT)
        parcel.writeString(CITY)
        parcel.writeString(CUST_CD)
        parcel.writeString(EMAIL_ADDR)
        parcel.writeString(FNAME)
        parcel.writeString(HOME_PHONE)
        parcel.writeString(LNAME)
        parcel.writeInt(ORDER_COUNT)
        parcel.writeString(ST_CD)
        parcel.writeString(ZIP_CD)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "$FNAME $LNAME, $HOME_PHONE"
    }

    companion object CREATOR : Parcelable.Creator<APIResponse> {
        override fun createFromParcel(parcel: Parcel): APIResponse {
            return APIResponse(parcel)
        }

        override fun newArray(size: Int): Array<APIResponse?> {
            return arrayOfNulls(size)
        }
    }
}