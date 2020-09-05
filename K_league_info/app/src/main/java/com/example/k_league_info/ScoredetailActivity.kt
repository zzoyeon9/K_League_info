package com.example.k_league_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k_league_info.Scoredetail.*
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_scoredetail.*
import org.json.JSONObject

class ScoredetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoredetail)

        val data = getData()
        init(data)
    }
    /**
     * @author : 최다윗
     * @throws : NULL
     * @return : ArrayList<HighlightModel> => ScoredetailActivity.initViewPager
     * @description : 임시적으로 json파일을 받아서 HighlightModel로 파싱하는 메소드
     * */
    private fun getData(): ArrayList<HighlightModel> {
        var str = """
            { "scoreDetail" : [
                {
                    "type" : 0,
                    "title" : "시작",
                    "time" : "오후4시",
                    "img" : 0,
                    "teamName" : "",
                    "player" : "",
                    "img2" : 0,
                    "teamName2" : "",
                    "player2" : "",
                    "contentString" : ""
                },
                {
                    "type" : 1,
                    "title" : "goal",
                    "time" : "80",
                    "img" : 0,
                    "teamName" : "서울",
                    "player" : "다윗",
                    "img2" : 0,
                    "teamName2" : "",
                    "player2" : "",
                    "contentString" : "패널티"
                },
                {
                    "type" : 2,
                    "title" : "옐로카드",
                    "time" : "20",
                    "img" : 0,
                    "teamName" : "서울",
                    "player" : "다윗",
                    "img2" : 0,
                    "teamName2" : "",
                    "player2" : "",
                    "contentString" : ""
                },
                {
                    "type" : 3,
                    "title" : "선수교체",
                    "time" : "50",
                    "img" : 0,
                    "teamName" : "서울",
                    "player" : "다윗",
                    "img2" : 0,
                    "teamName2" : "서울",
                    "player2" : "본승",
                    "contentString" : ""
                }
            ]}
        """.trimIndent()
        //json 형식을 CommunityBoard 형식으로 파싱하여 boardList에 삽입
        var boardList = arrayListOf<HighlightModel>()
        val gson = GsonBuilder().create()
        val jsonObject = JSONObject(str)
        val jsonArray = jsonObject.optJSONArray("scoreDetail")

        for (i in 0 until jsonArray.length()) {
            var board =
                gson.fromJson(jsonArray.getJSONObject(i).toString(), HighlightModel::class.java)
            boardList.add(board)
        }
        return boardList
    }

    private fun init(data: ArrayList<HighlightModel>) {
        val mVTAdapter = MultiViewTypeAdapter(data)
        rv_scoredetail.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_scoredetail.adapter = mVTAdapter
    }

}