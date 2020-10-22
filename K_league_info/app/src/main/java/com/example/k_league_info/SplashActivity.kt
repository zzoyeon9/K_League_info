package com.example.k_league_info

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.k_league_info.ui.community.CommunityBoard
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_splash.*
import org.json.JSONArray

class SplashActivity : AppCompatActivity() {
    private var retrofitClient = RetrofitClient()
    private val retrofit = retrofitClient.getInstance()
    private val restAPI: RetrofitNetwork = retrofit.create(RetrofitNetwork::class.java)
    private var communityList = ArrayList<CommunityBoard>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val thread = BringDataThread()
        thread.start()

    }
    inner class BringDataThread :Thread() {
        override fun run() {
            val loading : TextView = findViewById(R.id.loading)
            loading.text = "community Loading..."
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            val gson = GsonBuilder().create()
            val communityJSONArray = JSONArray(restAPI.getCommunity().execute().body().toString())
            for (i in 0 until communityJSONArray.length()) {
                val board = gson.fromJson(
                    communityJSONArray.getJSONObject(i).toString(),
                    CommunityBoard::class.java
                )
                communityList.add(board)
            }
            intent.putExtra("community", communityList)


            loading.text = "finish!!"
            startActivity(intent)
            finish()
        }
    }
}