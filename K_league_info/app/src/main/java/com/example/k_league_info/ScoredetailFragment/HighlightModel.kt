package com.example.k_league_info.ScoredetailFragment

//1 인자 viewTypeEnum
//2 인자 title
//3 인자 time
//4 인자 image(time_image or player_face or in_player_face)
//5 인자 image(out_player_face)
//6 인자 컨텐츠 텍스트
data class HighlightModel(
    val type: Int,
    val title: String,
    val time: String,
    val img: Int,
    val img2: Int,
    val contentString: String?
) {
    companion object {
        const val ITEM_TIME = 0
        const val ITEM_GOAL = 1
        const val ITEM_CARD = 2
        const val ITEM_SWITCH = 3
    }

}