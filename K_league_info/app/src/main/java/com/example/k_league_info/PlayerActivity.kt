package com.example.k_league_info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.activity_teamdetail.*
import kotlinx.android.synthetic.main.item_teamdetail.view.*

class PlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)


        var belong = getIntent().getStringExtra("belong")
        var name = getIntent().getStringExtra("name")
        var position = getIntent().getStringExtra("position")
        var birthdt = getIntent().getStringExtra("birthdt")
        var height = getIntent().getStringExtra("height")
        var weight = getIntent().getStringExtra("weight")
        var img_src = getIntent().getStringExtra("img_src")

        belong_data.setText(belong)
        name_data.setText(name)
        position_data.setText(position)
        birthdt_data.setText(birthdt)
        height_data.setText(height)
        weight_data.setText(weight)
        Glide.with(applicationContext).asBitmap().load(img_src).override(120, 120).fitCenter().into(playerImg)

        
        // 선수 사진 클릭시 나무위키에 선수 관련 정보로 이동
        val playerurl : String = "https://namu.wiki/w/" + name
        playerImg.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(playerurl)
            startActivity(i)
        }

    }
}