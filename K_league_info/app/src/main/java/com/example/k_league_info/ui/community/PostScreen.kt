package com.example.k_league_info.ui.community

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.k_league_info.R
import kotlinx.android.synthetic.main.activity_post_screen.*
import org.json.JSONObject

/*
    게시물을 클릭 시 보여질 화면
 */
class PostScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_screen)

        val num = intent.getIntExtra("num", 0)
        val titles = intent.getStringExtra("title")
        val contents = intent.getStringExtra("content")

        val t = findViewById<TextView>(R.id.title)
        t.text = titles.toString()
        content.text = contents.toString()

        //등록 버튼 클릭 시 입력된 댓글을 json으로 파싱하고 키보드를 내리고 editText를 clear
        ok.setOnClickListener {
            val json = JSONObject()
            json.put("comment", comment.text.toString())
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
            comment.text.clear()
        }
    }
}