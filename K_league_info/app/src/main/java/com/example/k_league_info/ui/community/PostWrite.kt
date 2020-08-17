package com.example.k_league_info.ui.community

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.k_league_info.R
import kotlinx.android.synthetic.main.activity_post_write.*
import org.json.JSONObject

/*
    게시물을 작성 시 보여질 화면
 */
class PostWrite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_write)

        ok.setOnClickListener {
            val json = JSONObject()
            json.put("title", titles.text.toString())
            json.put("content", content.text.toString())
            finish()
        }
        cancel.setOnClickListener {
            finish()
        }
    }
}