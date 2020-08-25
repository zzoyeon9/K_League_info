package com.example.k_league_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.k_league_info.ScoredetailFragment.*
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_scoredetail.*
import org.json.JSONObject

class ScoredetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoredetail)

        val data = getData()
        initViewPager(data)
    }

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
                    "contentString" : "",
                },
                {
                    "type" : 1,
                    "title" : "goal",
                    "time" : "80",
                    "img" : 0,
                    "teamName" : "",
                    "player" : "",
                    "img2" : 0,
                    "teamName2" : "",
                    "player2" : "",
                    "contentString" : "",
                },
                {
                    "type" : 2,
                    "title" : "옐로카드",
                    "time" : "20",
                    "img" : 0,
                    "teamName" : "",
                    "player" : "",
                    "img2" : 0,
                    "teamName2" : "",
                    "player2" : "",
                    "contentString" : "",
                },
                {
                    "type" : 3,
                    "title" : "선수교체",
                    "time" : "50",
                    "img" : 0,
                    "teamName" : "",
                    "player" : "",
                    "img2" : 0,
                    "teamName2" : "",
                    "player2" : "",
                    "contentString" : "",
                }
            ]}
        """.trimIndent()
        //json 형식을 CommunityBoard 형식으로 파싱하여 boardList에 삽입
        var boardList = arrayListOf<HighlightModel>()
        val gson = GsonBuilder().create()

        val jsonObject = JSONObject(str)
        val jsonArray = jsonObject.optJSONArray("scoreDetail")

        for (i in 0 until jsonArray.length() - 1) {
            var board =
                gson.fromJson(jsonArray.getJSONObject(i).toString(), HighlightModel::class.java)
            boardList.add(board)
        }
        return boardList
    }

    private fun initViewPager(data: ArrayList<HighlightModel>) {

        val list = mutableListOf<HighlightModel>().apply {
            add(HighlightModel(HighlightModel.ITEM_TIME, "시작","오후 4시",R.drawable.ic_launcher_foreground,"","",0,"","",null))
            add(HighlightModel(HighlightModel.ITEM_GOAL, "Goal","80\'",R.drawable.ic_launcher_foreground,"","",0,"","",null))
            add(HighlightModel(HighlightModel.ITEM_CARD, "옐로카드","10\'",R.drawable.ic_launcher_foreground,"","",0,"","",null))
            add(HighlightModel(HighlightModel.ITEM_SWITCH, "선수 교체","30\'",R.drawable.ic_launcher_foreground,"","",R.drawable.ic_launcher_foreground,"","",null))
        }

        val highlightFragment = FragmentHighlight(list)
        val recodeFragment = FragmentRecode()
        val lineupFragment = FragmentLineup()

//        initHighlight(highlightFragment)
        initRecode(recodeFragment)
        initLineUp(lineupFragment)

        val fragmentAdapter = ScoredetailFPA(supportFragmentManager)
        fragmentAdapter.addItems(highlightFragment)
        fragmentAdapter.addItems(recodeFragment)
        fragmentAdapter.addItems(lineupFragment)

        //뷰페이저 연결
        vp_scoredetail.adapter = fragmentAdapter
        //탭레이아웃 연결 (support:design)
        tl_scoredetail.setupWithViewPager(vp_scoredetail)

        tl_scoredetail.getTabAt(0)?.text = "Highlight"
        tl_scoredetail.getTabAt(1)?.text = "Recode"
        tl_scoredetail.getTabAt(2)?.text = "Lineup"
    }

    private fun initRecode(recodeFragment: FragmentRecode){
        recodeFragment.name = "Recode"
    }

    private fun initLineUp(lineupFragment: FragmentLineup){
        lineupFragment.name = "LineUp"
    }
}