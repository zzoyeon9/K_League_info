package com.example.k_league_info.ui.score

import android.os.Bundle
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

    private fun timeGenerator() :String{
        val day = Calendar.getInstance()
        val year = day.get(Calendar.YEAR).toString()
        var month = (day.get(Calendar.MONTH) + 1).toString()
        var date = day.get(Calendar.DATE).toString()
        if (month.toInt() < 10) {
            month = "0$month"
        }
        if (date.toInt() < 10) {
            date = "0$date"
        }
        return year + month + date
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var scheduleList = arrayListOf<ScoreBoard>()
        var day = timeGenerator().toInt()
        for(i in AppData.scoreList)
            if(day== i.date!!.toInt())
                scheduleList.add(i)

        val scoreAdapter = ScoreAdapter(requireContext(), scheduleList)
        score_recyclerView.adapter = scoreAdapter

        val lm = LinearLayoutManager(activity)
        score_recyclerView.layoutManager = lm
        score_recyclerView.setHasFixedSize(true)

        val cal = Calendar.getInstance()
        cal.time = Date()
        val format : DateFormat = SimpleDateFormat("MM.dd")

        calender.text = format.format(cal.time)

        next.setOnClickListener {
            cal.add(Calendar.DATE, 1)
            calender.text = format.format(cal.time)
            day++
            var scheduleList = arrayListOf<ScoreBoard>()
            for(i in AppData.scoreList)
                if(day== i.date!!.toInt())
                    scheduleList.add(i)

            val scoreAdapter = ScoreAdapter(requireContext(), scheduleList)
            score_recyclerView.adapter = scoreAdapter

            val lm = LinearLayoutManager(activity)
            score_recyclerView.layoutManager = lm
            score_recyclerView.setHasFixedSize(true)
        }

        previous.setOnClickListener {
            cal.add(Calendar.DATE, -1)
            calender.text = format.format(cal.time)
            day--
            var scheduleList = arrayListOf<ScoreBoard>()
            for(i in AppData.scoreList)
                if(day== i.date?.toInt())
                    scheduleList.add(i)

            val scoreAdapter = ScoreAdapter(requireContext(), scheduleList)
            score_recyclerView.adapter = scoreAdapter

            val lm = LinearLayoutManager(activity)
            score_recyclerView.layoutManager = lm
            score_recyclerView.setHasFixedSize(true)
        }
    }
}