package com.example.k_league_info.ui.community

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
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
            /*
            val progressDialog = ProgressDialog(this@PostWrite)
            progressDialog.setTitle("Kotlin Progress Bar")
            progressDialog.show()
            val progressBar = ProgressBar(this)
             */


            Toast.makeText(applicationContext,"글 작성중입니다.",Toast.LENGTH_LONG).show()
            val json = JSONObject()
            json.put("title", titles.text.toString())
            json.put("content", content.text.toString())
            val cb = CommunityBoard(0,titles.text.toString(),content.text.toString())
            api.setPost(cb).enqueue(object : Callback<Void>{
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(applicationContext,"실패하였습니다.",Toast.LENGTH_LONG).show()
                }
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(applicationContext,"글이 작성되었습니다.",Toast.LENGTH_LONG).show()
                    finish()
                }
            })
        }
    }
}