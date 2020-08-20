package com.example.k_league_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k_league_info.ScoredetailFragment.*
import kotlinx.android.synthetic.main.activity_scoredetail.*

class ScoredetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoredetail)

        initViewPager()
    }

    private fun initViewPager(){
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