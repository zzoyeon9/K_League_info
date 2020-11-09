package com.example.k_league_info.ui.score

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k_league_info.AppData
import com.example.k_league_info.R
import kotlinx.android.synthetic.main.fragment_score.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ScoreFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_score, container, false)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cal = Calendar.getInstance()
        cal.time = Date()
        val format: DateFormat = SimpleDateFormat("MM.dd")
        val dbFormat: DateFormat = SimpleDateFormat("yyyyMMdd")
        val scheduleList = arrayListOf<ScoreBoard>()

        for (i in AppData.scoreList)
            if (dbFormat.format(cal.time) == i.date)
                scheduleList.add(i)
        calender.text = format.format(cal.time)

        val scoreAdapter = ScoreAdapter(requireContext(), scheduleList)
        score_recyclerView.adapter = scoreAdapter

        val lm = LinearLayoutManager(activity)
        score_recyclerView.layoutManager = lm
        score_recyclerView.setHasFixedSize(true)

        next.setOnClickListener {
            scheduleList.clear()
            cal.add(Calendar.DATE, 1)

            for (i in AppData.scoreList)
                if (dbFormat.format(cal.time) == i.date)
                    scheduleList.add(i)

            calender.text = format.format(cal.time)
            scoreAdapter.notifyDataSetChanged()
        }

        previous.setOnClickListener {
            scheduleList.clear()
            cal.add(Calendar.DATE, -1)
            Log.d("log : cal = ",cal.time.toString())

            for (i in AppData.scoreList)
                if (dbFormat.format(cal.time) == i.date)
                    scheduleList.add(i)

            calender.text = format.format(cal.time)
            scoreAdapter.notifyDataSetChanged()
        }
    }
}