package com.example.exchangerate.presentation.rates.adapter.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.CONTENTS_FILE_DESCRIPTOR
import com.example.exchangerate.common.empty

data class CurrencyParcelable(
    val title: String,
    val buy: String,
    val sell: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: String.empty(),
        parcel.readString() ?: String.empty(),
        parcel.readString() ?: String.empty()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(title)
        dest?.writeString(buy)
        dest?.writeString(sell)
    }

    override fun describeContents() = CONTENTS_FILE_DESCRIPTOR

    companion object CREATOR : Parcelable.Creator<CurrencyParcelable> {
        override fun createFromParcel(parcel: Parcel): CurrencyParcelable {
            return CurrencyParcelable(parcel)
        }

        override fun newArray(size: Int): Array<CurrencyParcelable?> {
            return arrayOfNulls(size)
        }
    }
}