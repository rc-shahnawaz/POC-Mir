package com.example.myfirstandroidapp.model

import android.os.Parcel
import android.os.Parcelable

data class OrderHistory(
    val CUST_NAME: String?,
    val DEL_DOC_NUM: String?,
    val INVOICEACCOUNT: String?,
    val ORDER_SOURCE: String?,
    val ORDER_TYPE: String?,
    val SHIP_TO_ADDR1: String?,
    val SO_STORE_CD: String?,
    val SO_WR_DT: String?,
    val TOTAL_SALE: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(CUST_NAME)
        parcel.writeString(DEL_DOC_NUM)
        parcel.writeString(INVOICEACCOUNT)
        parcel.writeString(ORDER_SOURCE)
        parcel.writeString(ORDER_TYPE)
        parcel.writeString(SHIP_TO_ADDR1)
        parcel.writeString(SO_STORE_CD)
        parcel.writeString(SO_WR_DT)
        parcel.writeDouble(TOTAL_SALE)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderHistory> {
        override fun createFromParcel(parcel: Parcel): OrderHistory {
            return OrderHistory(parcel)
        }

        override fun newArray(size: Int): Array<OrderHistory?> {
            return arrayOfNulls(size)
        }
    }
}