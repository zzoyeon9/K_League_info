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
        TeamdetailBoard("eee", "name")
    )
    
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teamdetail)

        linearLayoutManager = LinearLayoutManager(this)
        fw_recyclerview.layoutManager = linearLayoutManager
        
        adapter = TeamdetailAdapter(boardlist)
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

