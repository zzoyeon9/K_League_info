package com.example.k_league_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.k_league_info.ScoredetailFragment.FragmentHighlight
import com.example.k_league_info.ScoredetailFragment.FragmentLineup
import com.example.k_league_info.ScoredetailFragment.FragmentRecode
import kotlinx.android.synthetic.main.activity_scoredetail.*

class ScoredetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoredetail)

        val fragmentAdapter = ScoredetailFPA(supportFragmentManager)
        //뷰페이저 연결
        vp_scoredetail.adapter = fragmentAdapter
        //탭레이아웃 연결 (support:design)
        tl_scoredetail.setupWithViewPager(vp_scoredetail)

        tl_scoredetail.getTabAt(0)?.text = "Highlight"
        tl_scoredetail.getTabAt(1)?.text = "Recode"
        tl_scoredetail.getTabAt(2)?.text = "Lineup"
    }
}