package com.example.k_league_info.ui.score

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k_league_info.R
import com.example.k_league_info.RetrofitClient
import com.example.k_league_info.RetrofitNetwork
import com.example.k_league_info.Scoredetail.HighlightModel
import com.example.k_league_info.ScoredetailActivity
import com.example.k_league_info.ui.community.CommunityBoard
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.fragment_community.*
import kotlinx.android.synthetic.main.fragment_score.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ScoreFragment : Fragment() {


    private var retrofitClient = RetrofitClient()
    private val retrofit = retrofitClient.getInstance()
    private val restAPI: RetrofitNetwork = retrofit.create(RetrofitNetwork::class.java)
    var gameList = arrayListOf<ScoreBoard>(

    )

    private fun PostList() {
        //비동기
        Runnable {
            restAPI.getPost2().enqueue(object : Callback<JsonArray>{
                //서버와 접속 실패
                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    Log.d("log",t.message)
                }
                //서버와 접속 성공
                override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                    val gson = GsonBuilder().create()

                    val jsonArray = JSONArray(response.body()!!.toString())
                    for (i in 0 until jsonArray.length()) {

                        var board = gson.fromJson(jsonArray.getJSONObject(i).toString(), ScoreBoard::class.java)
                        var boardList = arrayListOf<HighlightModel>()
                        val jsonArray1 = jsonArray.getJSONObject(i).getJSONArray("scoreDetail")

                        for (j in 0 until jsonArray1.length()) {
                            var board =
                                gson.fromJson(jsonArray1.getJSONObject(j).toString(), HighlightModel::class.java)
                            boardList.add(board)
                        }
                        board.scoreDetail = boardList
                        gameList.add(board)
                    }
                    score_recyclerView.adapter?.notifyDataSetChanged();
                }
            })
        }.run()
    }
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


    fun timeGenerator() :String{
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

        PostList()

        var scheduleList = arrayListOf<ScoreBoard>()
        var day = timeGenerator().toInt()
        for(i in gameList)
            if(day== i.date!!.toInt())
                scheduleList.add(i)

        val score_Adapter = ScoreAdapter(requireContext(), scheduleList)
        score_recyclerView.adapter = score_Adapter

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
            ++day
            var scheduleList = arrayListOf<ScoreBoard>()
            for(i in gameList)
                if(day== i.date!!.toInt())
                    scheduleList.add(i)

            val score_Adapter = ScoreAdapter(requireContext(), scheduleList)
            score_recyclerView.adapter = score_Adapter

            val lm = LinearLayoutManager(activity)
            score_recyclerView.layoutManager = lm
            score_recyclerView.setHasFixedSize(true)
        }

        previous.setOnClickListener {
            cal.add(Calendar.DATE, -1)
            calender.text = format.format(cal.time)
            --day
            var scheduleList = arrayListOf<ScoreBoard>()
            for(i in gameList)
                if(day== i.date?.toInt())
                    scheduleList.add(i)

            val score_Adapter = ScoreAdapter(requireContext(), scheduleList)
            score_recyclerView.adapter = score_Adapter

            val lm = LinearLayoutManager(activity)
            score_recyclerView.layoutManager = lm
            score_recyclerView.setHasFixedSize(true)
        }
    }
}