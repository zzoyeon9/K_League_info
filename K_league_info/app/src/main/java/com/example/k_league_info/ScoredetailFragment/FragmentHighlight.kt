package com.example.k_league_info.ScoredetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k_league_info.R
import kotlinx.android.synthetic.main.fragment_scoredetail_highlight.view.*

class FragmentHighlight(val list: MutableList<HighlightModel>) : Fragment() {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_scoredetail_highlight, container, false)
        recyclerView = view.findViewById(R.id.rv_scoredetail!!) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = multiAdapter()

        return view
    }

    private fun multiAdapter(): MultiViewTypeAdapter {

        return MultiViewTypeAdapter(list)
    }


}
