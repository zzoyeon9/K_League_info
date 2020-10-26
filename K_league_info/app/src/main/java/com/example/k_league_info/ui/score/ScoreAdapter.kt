package com.example.k_league_info.ui.score

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.k_league_info.R
import com.example.k_league_info.ScoredetailActivity
import java.util.*

class ScoreAdapter(val context: Context, val scheduleList: ArrayList<ScoreBoard>) :
    RecyclerView.Adapter<ScoreAdapter.Holder>() {
    //위젯들(ImageView, TextView)을 변수로 가져옴
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val homeImage = itemView.findViewById<ImageView>(R.id.homemark)
        val awayImage = itemView.findViewById<ImageView>(R.id.awaymark)
        val homeName = itemView.findViewById<TextView>(R.id.homename)
        val awayName = itemView.findViewById<TextView>(R.id.awayname)
        val Score = itemView.findViewById<TextView>(R.id.score)


        //이제 가져온 위젯들(ImageView, TextView)의 소스, text를 크롤링해온 데이터로 바꿔줌
        fun bind(scoreBoard: ScoreBoard, context: Context) {
// 홈팀 동기화

            homeName.text = scoreBoard.hometeam
            Score.text = scoreBoard.score
            var homeResName = "@drawable/"
            when (scoreBoard.hometeam) {
                "서울" -> homeResName += "seoul"
                "부산" -> homeResName += "busan"
                "광주" -> homeResName += "gwangju"
                "대구" -> homeResName += "daegu"
                "전북" -> homeResName += "jeonbuk"
                "전남" -> homeResName += "jeonnam"
                "경남" -> homeResName += "gyeonnam"
                "강원" -> homeResName += "gangwon"
                "울산" -> homeResName += "ulsan"
                "포항" -> homeResName += "pohang"
                "상주" -> homeResName += "sangju"
                "성남" -> homeResName += "seongnam"
                "제주" -> homeResName += "jeju"
                "수원" -> homeResName += "suwon"
                "인천" -> homeResName += "incheon"
            }
            val homeResId =
                homeImage.resources.getIdentifier(homeResName, "drawable", context.packageName)
            homeImage.setImageResource(homeResId)



//어웨이팀 동기화
            awayName.text = scoreBoard.awayteam
            var awayResName = "@drawable/"
            when (scoreBoard.awayteam) {
                "서울" -> awayResName += "seoul"
                "부산" -> awayResName += "busan"
                "광주" -> awayResName += "gwangju"
                "대구" -> awayResName += "daegu"
                "전북" -> awayResName += "jeonbuk"
                "전남" -> awayResName += "jeonnam"
                "강원" -> awayResName += "gangwon"
                "울산" -> awayResName += "ulsan"
                "포항" -> awayResName += "pohang"
                "상주" -> awayResName += "sangju"
                "성남" -> awayResName += "seongnam"
                "제주" -> awayResName += "jeju"
                "수원" -> awayResName += "suwon"
                "인천" -> awayResName += "incheon"
            }
            val awayResId =
                awayImage.resources.getIdentifier(awayResName, "drawable", context.packageName)
            awayImage.setImageResource(awayResId)
            itemView.setOnClickListener {
                val intent = Intent(context, ScoredetailActivity::class.java)
                intent.putExtra("homeTeam", scoreBoard.hometeam)
                intent.putExtra("awayTeam", scoreBoard.awayteam)
                intent.putExtra("score", scoreBoard.score)
                intent.putExtra("date", scoreBoard.date)
                intent.putExtra("scoreDetail", scoreBoard.scoreDetail)
                context.startActivity(intent)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_score, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(scheduleList[position], context)
    }


}


