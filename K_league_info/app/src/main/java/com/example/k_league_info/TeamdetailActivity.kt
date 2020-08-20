package com.example.k_league_info

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.LinearLayout.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_teamdetail.*

class TeamdetailActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: TeamdetailAdapter
    private val boardlist = arrayListOf<TeamdetailBoard>(
        TeamdetailBoard("https://i.pinimg.com/originals/60/00/35/600035c0e351085fced5e3473da3a147.jpg", "ryan01"),
        TeamdetailBoard("https://i.pinimg.com/originals/bc/6f/64/bc6f6464d2abe64a7eb3e940654e1b3a.png","ryan02"),
        TeamdetailBoard("https://i.pinimg.com/474x/96/48/e9/9648e97d392b54acbef76ccacbfffc12.jpg","ryan03"),
        TeamdetailBoard("https://i.pinimg.com/originals/8a/e8/8e/8ae88e20a679dd60f5d6f237039bee08.jpg","ryan04")
    )


    /**
     * @author : 구본승
     * @description : intent로 넘어온 data처리 & Layoutmanager 사용하여 horizontal 형태의 recyclerview 설정 & adapter 사용
     * */
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teamdetail)

        var teamName: String? = getIntent().getStringExtra("teamName")
        teamLogo.setImageResource(baseContext.resources.getIdentifier(teamName, "drawable", baseContext.packageName))

        linearLayoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        fw_recyclerview.layoutManager = linearLayoutManager
        
        adapter = TeamdetailAdapter(baseContext, boardlist)
        fw_recyclerview.adapter = adapter

    }


    /**
     * @author : 구본승
     * @description : 만약 시작시 데이터가 없다면 toast 메시지로 404 를 띄운다
     * */
    override fun onStart() {
        super.onStart()
        if(boardlist.size == 0){
            Toast.LENGTH_SHORT.toString(404)
        }
    }
}

