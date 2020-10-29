package com.example.k_league_info

import com.example.k_league_info.ui.community.CommunityBoard
import com.example.k_league_info.ui.score.ScoreBoard

object AppData {
    private val retrofitClient = RetrofitClient()
    private val retrofit = retrofitClient.getInstance()
    val restAPI: RetrofitNetwork = retrofit.create(RetrofitNetwork::class.java)

    var communityList = ArrayList<CommunityBoard>()
    var refreshData = false

    var scoreList = ArrayList<ScoreBoard>()

}