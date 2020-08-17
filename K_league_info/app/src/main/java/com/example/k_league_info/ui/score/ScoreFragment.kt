package com.example.k_league_info.ui.score

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k_league_info.MainActivity
import com.example.k_league_info.R
import com.example.k_league_info.ScoredetailActivity
import com.example.k_league_info.TeamdetailActivity
import com.example.k_league_info.ui.community.CommunityBoard
import kotlinx.android.synthetic.main.fragment_score.*

class ScoreFragment : Fragment() {

    var gameList = arrayListOf<ScoreBoard>()

    var boardList = arrayListOf<ScoreBoard>(
        ScoreBoard("제목자리","내용자리","추천수", "댓글수")


    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_score, container,false)
        val rootView2 = inflater.inflate(R.layout.item_score, container,false)

        val score_Adapter = ScoreAdapter(requireContext(), boardList)
        val myrecycler = rootView.findViewById(R.id.score_recyclerView!!)as RecyclerView
        myrecycler.layoutManager = LinearLayoutManager(requireContext())
        myrecycler.adapter = score_Adapter


        var scoreCard = rootView2.findViewById<CardView>(R.id.score_card)
        scoreCard.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, ScoredetailActivity::class.java)
                startActivity(nextIntent)
            }
        }


        return rootView2
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}