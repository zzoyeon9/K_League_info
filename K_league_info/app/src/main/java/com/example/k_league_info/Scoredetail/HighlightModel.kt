package com.example.k_league_info.Scoredetail

import android.os.Parcel
import android.os.Parcelable

//1 인자 viewTypeEnum - 시작시간, 골, 선수교체, 경고카드 4가지 분류
//2 인자 title - 시작시간, 골, 선수교체, 경고카드
//3 인자 time - 시작시간
//4 인자 image(time_image or player_face or in_player_face) - 시작시간, 골, 선수교체, 경고카드 첫 이미지
//5 인자 team_name
//6 인자 player
//7 인자 image(out_player_face)
//8 인자 team_name
//9 인자 player
//10 인자 컨텐츠 텍스트  - 골의 종류 (패널티, 슛)

data class HighlightModel(
    val type: Int,
    val title: String?,
    val time: String?,
    val img: Int,
    val teamName: String?,
    val player: String?,
    val img2: Int,
    val teamName2: String?,
    val player2: String?,
    val contentString: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(type)
        parcel.writeString(title)
        parcel.writeString(time)
        parcel.writeInt(img)
        parcel.writeString(teamName)
        parcel.writeString(player)
        parcel.writeInt(img2)
        parcel.writeString(teamName2)
        parcel.writeString(player2)
        parcel.writeString(contentString)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HighlightModel> {
        const val ITEM_TIME = 0
        const val ITEM_GOAL = 1
        const val ITEM_CARD = 2
        const val ITEM_SWITCH = 3
        override fun createFromParcel(parcel: Parcel): HighlightModel {
            return HighlightModel(parcel)
        }

        override fun newArray(size: Int): Array<HighlightModel?> {
            return arrayOfNulls(size)
        }
    }

}