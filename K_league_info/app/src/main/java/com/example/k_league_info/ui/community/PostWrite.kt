package com.example.k_league_info.ui.community

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.k_league_info.R
import com.example.k_league_info.RetrofitClient
import com.example.k_league_info.RetrofitNetwork
import kotlinx.android.synthetic.main.activity_post_write.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
    게시물을 작성 시 보여질 화면
 */
class PostWrite : AppCompatActivity() {
    private var retrofitClient = RetrofitClient()
    private val retrofit = retrofitClient.getInstance()
    private val api: RetrofitNetwork = retrofit.create(RetrofitNetwork::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_write)

        ok.setOnClickListener {

            val json = JSONObject()
            json.put("title", titles.text.toString())
            json.put("content", content.text.toString())

            api.setPost(json).enqueue(object : Callback<JSONObject>{
                override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                    Log.d("log", t.message)
                }

                override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                    Log.d("log",response.headers().toString())
                }

            })
            Toast.makeText(applicationContext,"글이 작성되었습니다.",Toast.LENGTH_LONG).show()
            finish()
        }
    }
}