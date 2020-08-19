package com.example.k_league_info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
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
    
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teamdetail)

        linearLayoutManager = LinearLayoutManager(this)
        fw_recyclerview.layoutManager = linearLayoutManager
        
        adapter = TeamdetailAdapter(baseContext, boardlist)
        fw_recyclerview.adapter = adapter

    }

    // 만약 시작시 데이터가 없다면 toast 메시지로 404 를 띄운다
    override fun onStart() {
        super.onStart()
        if(boardlist.size == 0){
            Toast.LENGTH_SHORT.toString(404)
        }
    }
}

