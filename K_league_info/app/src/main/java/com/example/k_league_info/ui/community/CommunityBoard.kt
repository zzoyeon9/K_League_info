package com.example.k_league_info.ui.community

import android.os.Parcel
import android.os.Parcelable

class CommunityBoard(
    val number: Int,
    val title: String?,
    val content: String?,
    val password: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(number)
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CommunityBoard> {
        override fun createFromParcel(parcel: Parcel): CommunityBoard {
            return CommunityBoard(parcel)
        }

        override fun newArray(size: Int): Array<CommunityBoard?> {
            return arrayOfNulls(size)
        }
    }
}