package com.example.k_league_info

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
    private val retrofit = retrofitClient.getInstance()
    private val api: RetrofitNetwork = retrofit.create(
        RetrofitNetwork::class.java
    )


//    private val stafflist: ArrayList<TeamdetailBoard> = arrayListOf<TeamdetailBoard>()
    private val fwlist: ArrayList<TeamdetailBoard> = arrayListOf<TeamdetailBoard>()
    private val mflist: ArrayList<TeamdetailBoard> = arrayListOf<TeamdetailBoard>()
    private val dflist: ArrayList<TeamdetailBoard> = arrayListOf<TeamdetailBoard>()
    private val gklist: ArrayList<TeamdetailBoard> = arrayListOf<TeamdetailBoard>()


    private fun PostList(teamName : String) {
        var tName : String = ""
        // 팀 이름 한글 or 영어 조회 가능하게
        if(teamName == "suwon"){
            tName = "수원"
        } else if(teamName == "jeonbuk") {
            tName = "전북"
        } else if(teamName == "seoul"){
            tName = "서울"
        } else if(teamName == "busan"){
            tName = "부산"
        } else if(teamName == "daegu"){
            tName = "대구"
        } else if(teamName == "gwangju"){
            tName = "광주"
        } else if(teamName == "gangwon"){
            tName = "강원"
        } else if(teamName == "incheon"){
            tName = "인천"
        } else if(teamName == "pohang"){
            tName = "포항"
        } else if(teamName == "sangju"){
            tName = "상주"
        } else if(teamName == "seongnam"){
            tName = "성남"
        } else if(teamName == "ulsan"){
            tName = "울산"
        }
        //비동기
        Runnable {
            api.getTeamDetail().enqueue(object : Callback<JsonArray> {
                //서버와 접속 실패
                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    Log.d("log", t.message)
                }

                //서버와 접속 성공
                override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                    val gson = GsonBuilder().create()
//                    Log.d("log : @@@@@@@@@@", response.toString())
//                    Log.d("log : 이건 뭐냐 ? ", response.body().toString())
                    //json 형식을 TeamdetailBoard 형식으로 파싱하여 boardlist에 삽입
                    val jsonArray = JSONArray(response.body().toString())
                    for (i in 0 until jsonArray.length()) {
                        var board = gson.fromJson(
                            jsonArray.getJSONObject(i).toString(),
                            TeamdetailBoard::class.java
                        )
//                        Log.d("log : board 나오나? ", board.toString())
//                        Log.d("log : Booard.pos ", board.position.toString())
//                        Log.d("log : ?? ", tName)
//                        Log.d("log : && ", board.belong.toString())
                        if(board.belong.toString() == tName || board.belong.toString() == teamName) {
//                            Log.d("log : 나오냐 ? ", board.position.toString())
                            if (board.position.toString() == "FW" || board.position.toString() == "공격수") {
                                fwlist.add(board)
                            }
                            if (board.position.toString().equals("MF") || board.position.toString() == "미드필더") {
                                mflist.add(board)
                            }
                            if (board.position.toString().equals("DF") || board.position.toString() == "수비수") {
                                dflist.add(board)
                            }
                            if (board.position.toString().equals("GK") || board.position.toString() == "골키퍼") {
                                gklist.add(board)
                            }
                        }
                    }

                    Log.d("log : fw : ", fwlist.size.toString())
                    Log.d("log : mf : ", mflist.size.toString())
                    Log.d("log : df : ", dflist.size.toString())
                    Log.d("log : gk : ", gklist.size.toString())

//                    staff_recyclerview.adapter?.notifyDataSetChanged()
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teamdetail)



        var teamName: String = getIntent().getStringExtra("teamName")
        PostList(teamName)
        teamLogo.setImageResource(baseContext.resources.getIdentifier(teamName,"drawable",baseContext.packageName))

//         //Staff recyclerView
//        linearLayoutManager = LinearLayoutManager(this, HORIZONTAL, false)
//        staff_recyclerview.layoutManager = linearLayoutManager
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

//        // Staff recyclerview adapter
//        adapter = TeamdetailAdapter(baseContext, stafflist)
//        staff_recyclerview.adapter = adapter
        // FW recyclerview adapter
        adapter = TeamdetailAdapter(baseContext, fwlist)
        fw_recyclerview.adapter = adapter
        // MF recyclerview adapter
        adapter = TeamdetailAdapter(baseContext, mflist)
        mf_recyclerview.adapter = adapter
        // DF recyclerview adapter
        adapter = TeamdetailAdapter(baseContext, dflist)
        df_recyclerview.adapter = adapter
        // GK recyclerview adapter
        adapter = TeamdetailAdapter(baseContext, gklist)
        gk_recyclerview.adapter = adapter

    }
}

