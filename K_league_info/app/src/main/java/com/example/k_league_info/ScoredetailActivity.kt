package com.example.k_league_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import com.example.k_league_info.Scoredetail.*
import kotlinx.android.synthetic.main.activity_scoredetail.*
import com.example.k_league_info.ui.score.ScoreBoard

class ScoredetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoredetail)
        //받아온 scoreBoard
<<<<<<< HEAD
        val data = intent.getParcelableExtra<ScoreBoard>("score")
        score_1.text = data.homescore
        score_2.text = data.awayscore
        var homeResName = "@drawable/"
        val homeKorToEng = data.homename
        when (homeKorToEng) {
            "서울" -> homeResName += "seoul"
            "부산" -> homeResName += "busan"
            "광주" -> homeResName += "gwangju"
            "대구" -> homeResName += "daegu"
            "전북" -> homeResName += "jeonbuk"
            "전남" -> homeResName += "jeonnam"
            "경남" -> homeResName += "gyeonnam"
            "강원" -> homeResName += "gangwon"
            "울산" -> homeResName += "ulsan"
            "포항" -> homeResName += "pohang"
            "상주" -> homeResName += "sangju"
            "성남" -> homeResName += "seongnam"
            "제주" -> homeResName += "jeju"
            "수원" -> homeResName += "suwon"
            "인천" -> homeResName += "incheon"
        }
        val homeResId =
            team_logo_1.resources.getIdentifier(homeResName, "drawable",packageName)
        team_logo_1.setImageResource(homeResId)

        var awayResName = "@drawable/"
        val awayKorToEng = data.awayname
        when (awayKorToEng) {
=======
        score.text = intent.getStringExtra("score")

        var homeResName = "@drawable/"
        when (intent.getStringExtra("homeTeam")) {
            "서울" -> homeResName += "seoul"
            "부산" -> homeResName += "busan"
            "광주" -> homeResName += "gwangju"
            "대구" -> homeResName += "daegu"
            "전북" -> homeResName += "jeonbuk"
            "전남" -> homeResName += "jeonnam"
            "경남" -> homeResName += "gyeonnam"
            "강원" -> homeResName += "gangwon"
            "울산" -> homeResName += "ulsan"
            "포항" -> homeResName += "pohang"
            "상주" -> homeResName += "sangju"
            "성남" -> homeResName += "seongnam"
            "제주" -> homeResName += "jeju"
            "수원" -> homeResName += "suwon"
            "인천" -> homeResName += "incheon"
        }
        val homeResId =
            team_logo_1.resources.getIdentifier(homeResName, "drawable",packageName)
        team_logo_1.setImageResource(homeResId)

        var awayResName = "@drawable/"
        when (intent.getStringExtra("awayTeam")) {
>>>>>>> upstream/develop
            "서울" -> awayResName += "seoul"
            "부산" -> awayResName += "busan"
            "광주" -> awayResName += "gwangju"
            "대구" -> awayResName += "daegu"
            "전북" -> awayResName += "jeonbuk"
            "전남" -> awayResName += "jeonnam"
            "강원" -> awayResName += "gangwon"
            "울산" -> awayResName += "ulsan"
            "포항" -> awayResName += "pohang"
            "상주" -> awayResName += "sangju"
            "성남" -> awayResName += "seongnam"
            "제주" -> awayResName += "jeju"
            "수원" -> awayResName += "suwon"
            "인천" -> awayResName += "incheon"
        }
        val awayResId =
            team_logo_2.resources.getIdentifier(awayResName, "drawable", packageName)
        team_logo_2.setImageResource(awayResId)

<<<<<<< HEAD
        init(data.scoreDetail)
=======
        init(intent.getSerializableExtra("scoreDetail") as ArrayList<HighlightModel>)
>>>>>>> upstream/develop
    }


    private fun init(data: ArrayList<HighlightModel>) {
        val mVTAdapter = MultiViewTypeAdapter(data)
        rv_scoredetail.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_scoredetail.adapter = mVTAdapter
    }

}