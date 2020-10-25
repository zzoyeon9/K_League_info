package com.example.k_league_info.ui.score

import android.os.Parcel
import android.os.Parcelable
import com.example.k_league_info.Scoredetail.HighlightModel
import kotlin.collections.ArrayList

data class ScoreBoard(
    val hometeam: String?,
    val awayteam: String?,
    var score: String?,
    var date: String?,
    var scoreDetail: ArrayList<HighlightModel>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        TODO("scoreDetail")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(hometeam)
        parcel.writeString(awayteam)
        parcel.writeString(score)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ScoreBoard> {
        override fun createFromParcel(parcel: Parcel): ScoreBoard {
            return ScoreBoard(parcel)
        }

        override fun newArray(size: Int): Array<ScoreBoard?> {
            return arrayOfNulls(size)
        }
    }


}
