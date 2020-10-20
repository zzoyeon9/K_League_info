package com.example.k_league_info

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k_league_info.ui.community.CommunityBoard
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.activity_teamdetail.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamdetailActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: TeamdetailAdapter
    private var retrofitClient = RetrofitClient()
    private val retrofit = retrofitClient.instance
    private val api: RetrofitNetwork = retrofit.create(
        RetrofitNetwork::class.java)

    private val boardlist = arrayListOf<TeamdetailBoard>(
//        TeamdetailBoard("https://i.pinimg.com/originals/60/00/35/600035c0e351085fced5e3473da3a147.jpg", "ryan01"),
//        TeamdetailBoard("https://i.pinimg.com/originals/bc/6f/64/bc6f6464d2abe64a7eb3e940654e1b3a.png","ryan02"),
//        TeamdetailBoard("https://i.pinimg.com/474x/96/48/e9/9648e97d392b54acbef76ccacbfffc12.jpg","ryan03"),
//        TeamdetailBoard("https://i.pinimg.com/originals/8a/e8/8e/8ae88e20a679dd60f5d6f237039bee08.jpg","ryan04")
    )


    private fun PostList() {
        //비동기
        Runnable {
            api.getPost().enqueue(object : Callback<JsonArray> {
                //서버와 접속 실패
                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    Log.d("log",t.message)
                }
                //서버와 접속 성공
                override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                    val gson = GsonBuilder().create()
                    //json 형식을 TeamdetailBoard 형식으로 파싱하여 boardlist에 삽입
                    val jsonArray = JSONArray(response.body()!!.toString())
                    for (i in 0 until jsonArray.length()) {
                        var board = gson.fromJson(jsonArray.getJSONObject(i).toString(), TeamdetailBoard::class.java)
                        boardlist.add(board)
                    }
                    staff_recyclerview.adapter?.notifyDataSetChanged()
                    fw_recyclerview.adapter?.notifyDataSetChanged()
                    mf_recyclerview.adapter?.notifyDataSetChanged()
                    df_recyclerview.adapter?.notifyDataSetChanged()
                    gk_recyclerview.adapter?.notifyDataSetChanged()
                }
            })
        }.run()
    }

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

        // Staff recyclerView
        linearLayoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        staff_recyclerview.layoutManager = linearLayoutManager
        // FW recyclerView
        linearLayoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        fw_recyclerview.layoutManager = linearLayoutManager
        // MF recyclerView
        linearLayoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        mf_recyclerview.layoutManager = linearLayoutManager
        // DF recyclerView
        linearLayoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        df_recyclerview.layoutManager = linearLayoutManager
        // GK recyclerView
        linearLayoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        gk_recyclerview.layoutManager = linearLayoutManager

        // Staff recyclerview adapter
        adapter = TeamdetailAdapter(baseContext, boardlist)
        staff_recyclerview.adapter = adapter
        // FW recyclerview adapter
        adapter = TeamdetailAdapter(baseContext, boardlist)
        fw_recyclerview.adapter = adapter
        // MF recyclerview adapter
        adapter = TeamdetailAdapter(baseContext, boardlist)
        mf_recyclerview.adapter = adapter
        // DF recyclerview adapter
        adapter = TeamdetailAdapter(baseContext, boardlist)
        df_recyclerview.adapter = adapter
        // GK recyclerview adapter
        adapter = TeamdetailAdapter(baseContext, boardlist)
        gk_recyclerview.adapter = adapter

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

