package com.example.k_league_info

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.k_league_info.Scoredetail.HighlightModel
import com.example.k_league_info.ui.community.CommunityBoard
import com.example.k_league_info.ui.score.ScoreBoard
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_splash.*
import org.json.JSONArray

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        BringDataThread().start()
    }
    inner class BringDataThread :Thread() {
        override fun run() {
            loading.text = "community Loading..."
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            val gson = GsonBuilder().create()
            val communityJSONArray = JSONArray(AppData.restAPI.getCommunity().execute().body().toString())
            for (i in 0 until communityJSONArray.length()) {
                val community = gson.fromJson(
                    communityJSONArray.getJSONObject(i).toString(),
                    CommunityBoard::class.java
                )
                AppData.communityList.add(community)
            }
            loading.text = "score Loading..."
            val scoreJSONArray = JSONArray(AppData.restAPI.getScore().execute().body().toString())
            for (i in 0 until scoreJSONArray.length()) {
                val score = gson.fromJson(scoreJSONArray.getJSONObject(i).toString(), ScoreBoard::class.java)
                val highlightModelList = arrayListOf<HighlightModel>()
                val scoreDetailJSONArray = scoreJSONArray.getJSONObject(i).getJSONArray("scoreDetail")
                for (j in 0 until scoreDetailJSONArray.length()) {
                    var highlightModel =
                        gson.fromJson(scoreDetailJSONArray.getJSONObject(j).toString(), HighlightModel::class.java)
                    highlightModelList.add(highlightModel)
                }
                score.scoreDetail = highlightModelList
                AppData.scoreList.add(score)
            }
            loading.text = "finish!!"
            startActivity(intent)
            finish()
        }
    }
}