package com.example.k_league_info.ui.score

import com.example.k_league_info.Scoredetail.HighlightModel

data class ScoreBoard(
    val hometeam: String?,
    val awayteam: String?,
    var score: String?,
    var date: String?,
    var scoreDetail: ArrayList<HighlightModel>
)
