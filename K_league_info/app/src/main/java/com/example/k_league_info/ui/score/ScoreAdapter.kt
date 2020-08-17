package com.example.k_league_info.ui.score

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.k_league_info.R
import kotlinx.android.synthetic.main.item_score.view.*


class ScoreAdapter (val context: Context,val scheduleList: ArrayList<ScoreBoard>) :
    RecyclerView.Adapter<ScoreAdapter.Holder>() {
    //위젯들(ImageView, TextView)을 변수로 가져옴
    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val homeImage = itemView?.findViewById<ImageView>(R.id.homemark)
        val homeName = itemView?.findViewById<TextView>(R.id.homename)
        val awayImage = itemView?.findViewById<ImageView>(R.id.awaymark)
        val awayName = itemView?.findViewById<TextView>(R.id.awayname)


        //이제 가져온 위젯들(ImageView, TextView)의 소스, text를 크롤링해온 데이터로 바꿔줌
        fun bind (ScoreBoard: ScoreBoard, context: Context){

            if(ScoreBoard.homename != "") {
                homeName!!.text = ScoreBoard.homename
                val mark = "R.drawable." + ScoreBoard.homename
                        homeImage!!.setImageResource(R.drawable.seoul)//이부분을 팀마크 파일 이름이 seoul.png면 데이터 크롤링해온 서울FC와 스트링을 똑같이 해서 고대로 복사 안되나? ex. R.drawable. + homename 이런시그로

                        }
            if(ScoreBoard.awayname != "") {
                awayName!!.text = ScoreBoard.awayname
                val mark = "R.drawable." + ScoreBoard.awayname
                awayImage!!.setImageResource(R.drawable.gyeongnam)//이부분을 팀마크 파일 이름이 seoul.png면 데이터 크롤링해온 서울FC와 스트링을 똑같이 해서 고대로 복사 안되나? ex. R.drawable. + homename 이런시그로

            }
        }


        }

    interface ItemClickListener
    {
        fun onClick(view: View, position: Int)
    }
    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
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
       /* holder.itemView.setOnClickListener{
            itemClickListener!!.onClick(it, position)
        }*/
    }


}


