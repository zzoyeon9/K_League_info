package com.example.k_league_info.ui.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k_league_info.R
import kotlinx.android.synthetic.main.fragment_score.*

class ScoreFragment : Fragment() {

    var gameList = arrayListOf<ScoreBoard>()

    var boardList = arrayListOf<ScoreBoard>(
        ScoreBoard("서울", "강원", "3", "2"),
        ScoreBoard("대구", "울산","4", "5"),
        ScoreBoard("전북", "포항","0", "1"),
        ScoreBoard("수원", "성남", "2", "2")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_score, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scoreAdapter = activity?.let { ScoreAdapter(it, boardList) }
        score_recyclerView.adapter = scoreAdapter

        val lm = LinearLayoutManager(activity)
        score_recyclerView.layoutManager = lm
        score_recyclerView.setHasFixedSize(true)
    }
}