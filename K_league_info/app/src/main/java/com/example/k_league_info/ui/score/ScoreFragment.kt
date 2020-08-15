package com.example.k_league_info.ui.score

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
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
        val score_Adapter = ScoreAdapter(requireContext(), boardList)
        val myrecycler = rootView.findViewById(R.id.score_recyclerView!!)as RecyclerView
        myrecycler.layoutManager = LinearLayoutManager(requireContext())
        myrecycler.adapter = score_Adapter

        var button = rootView.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, ScoredetailActivity::class.java)
                startActivity(nextIntent)
            }
        }

        /*score_Adapter.setItemClickListener(object : ScoreAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                val intent = Intent()//Dawith.fragment
                startActivity(intent)
            }
        })*/
        return inflater.inflate(R.layout.fragment_score, null)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}