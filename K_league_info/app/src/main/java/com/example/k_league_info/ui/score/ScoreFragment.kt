package com.example.k_league_info.ui.score

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k_league_info.R
import com.example.k_league_info.ScoredetailActivity
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

        val rootView = inflater.inflate(R.layout.fragment_score, container, false)
        val rootView2 = inflater.inflate(R.layout.item_score, container, false)




        var scoreCard = rootView2.findViewById<CardView>(R.id.score_card)
        scoreCard.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, ScoredetailActivity::class.java)
                startActivity(nextIntent)
            }
        }

        return inflater.inflate(R.layout.fragment_score, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val score_Adapter = ScoreAdapter(requireContext(), boardList)
        score_recyclerView.adapter = score_Adapter

        val lm = LinearLayoutManager(activity)
        score_recyclerView.layoutManager = lm
        score_recyclerView.setHasFixedSize(true)

    }
}