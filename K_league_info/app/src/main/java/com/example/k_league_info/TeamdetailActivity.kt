package com.example.k_league_info

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout.*
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

    private val fwlist: ArrayList<TeamdetailBoard> = arrayListOf<TeamdetailBoard>()
    private val mflist: ArrayList<TeamdetailBoard> = arrayListOf<TeamdetailBoard>()
    private val dflist: ArrayList<TeamdetailBoard> = arrayListOf<TeamdetailBoard>()
    private val gklist: ArrayList<TeamdetailBoard> = arrayListOf<TeamdetailBoard>()

    /**
     * @author : 구본승
     * @description : 팀 이름 한글 or 영어 조회 가능하게
     * */
    private fun teamNameFun(teamName: String?): String {
        if (teamName == "suwon") {
            return "수원"
        } else if (teamName == "jeonbuk") {
            return "전북"
        } else if (teamName == "seoul") {
            return "서울"
        } else if (teamName == "busan") {
            return "부산"
        } else if (teamName == "daegu") {
            return "대구"
        } else if (teamName == "gwangju") {
            return "광주"
        } else if (teamName == "gangwon") {
            return "강원"
        } else if (teamName == "incheon") {
            return "인천"
        } else if (teamName == "pohang") {
            return "포항"
        } else if (teamName == "sangju") {
            return "상주"
        } else if (teamName == "seongnam") {
            return "성남"
        } else if (teamName == "ulsan") {
            return "울산"
        }
        return ""
    }

    private fun PostList(teamName: String) {
        var tName = teamNameFun(teamName)
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
                    //json 형식을 TeamdetailBoard 형식으로 파싱하여 boardlist에 삽입
                    val jsonArray = JSONArray(response.body().toString())
                    for (i in 0 until jsonArray.length()) {
                        var board = gson.fromJson(
                            jsonArray.getJSONObject(i).toString(),
                            TeamdetailBoard::class.java
                        )
                        if (board.belong.toString() == tName || board.belong.toString() == teamName) {
                            if (board.position.toString() == "FW" || board.position.toString() == "공격수") {
                                fwlist.add(board)
                            }
                            if (board.position.toString()
                                    .equals("MF") || board.position.toString() == "미드필더"
                            ) {
                                mflist.add(board)
                            }
                            if (board.position.toString()
                                    .equals("DF") || board.position.toString() == "수비수"
                            ) {
                                dflist.add(board)
                            }
                            if (board.position.toString()
                                    .equals("GK") || board.position.toString() == "골키퍼"
                            ) {
                                gklist.add(board)
                            }
                        }
                    }

                    Log.d("log : fw : ", fwlist.size.toString())
                    Log.d("log : mf : ", mflist.size.toString())
                    Log.d("log : df : ", dflist.size.toString())
                    Log.d("log : gk : ", gklist.size.toString())

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
     * @description : teamLogo를 클릭시 해당 팀에 대한 자세한 정보를 제공하기 위한 url 연결
     * */
    private fun findurl(teamName: String?): String {
        var tName = teamNameFun(teamName)
        if (teamName == "suwon" || tName == "수원") {
            return "https://namu.wiki/w/%EC%88%98%EC%9B%90%20%EC%82%BC%EC%84%B1%20%EB%B8%94%EB%A3%A8%EC%9C%99%EC%A6%88?from=%EC%88%98%EC%9B%90%EC%82%BC%EC%84%B1"
        } else if (teamName == "jeonbuk" || tName == "전북") {
            return "https://namu.wiki/w/%EC%A0%84%EB%B6%81%20%ED%98%84%EB%8C%80%20%EB%AA%A8%ED%84%B0%EC%8A%A4?from=%EC%A0%84%EB%B6%81%ED%98%84%EB%8C%80"
        } else if (teamName == "seoul" || tName == "서울") {
            return "https://namu.wiki/w/FC%20%EC%84%9C%EC%9A%B8?from=%EC%84%9C%EC%9A%B8fc"
        } else if (teamName == "busan" || tName == "부산") {
            return "https://namu.wiki/w/%EB%B6%80%EC%82%B0%20%EC%95%84%EC%9D%B4%ED%8C%8C%ED%81%AC"
        } else if (teamName == "daegu" || tName == "대구") {
            return "https://namu.wiki/w/%EB%8C%80%EA%B5%AC%20FC?from=%EB%8C%80%EA%B5%ACFC"
        } else if (teamName == "gwangju" || tName == "광주") {
            return "https://namu.wiki/w/%EA%B4%91%EC%A3%BC%20FC?from=%EA%B4%91%EC%A3%BCfc"
        } else if (teamName == "gangwon" || tName == "강원") {
            return "https://namu.wiki/w/%EA%B0%95%EC%9B%90%20FC?from=%EA%B0%95%EC%9B%90FC"
        } else if (teamName == "incheon" || tName == "인천") {
            return "https://namu.wiki/w/%EC%9D%B8%EC%B2%9C%20%EC%9C%A0%EB%82%98%EC%9D%B4%ED%8B%B0%EB%93%9C%20FC?from=%EC%9D%B8%EC%B2%9C%EC%9C%A0%EB%82%98%EC%9D%B4%ED%8B%B0%EB%93%9CFC"
        } else if (teamName == "pohang" || tName == "포항") {
            return "https://namu.wiki/w/%ED%8F%AC%ED%95%AD%20%EC%8A%A4%ED%8B%B8%EB%9F%AC%EC%8A%A4"
        } else if (teamName == "sangju" || tName == "상주") {
            return "https://namu.wiki/w/%EC%83%81%EC%A3%BC%20%EC%83%81%EB%AC%B4?from=%EC%83%81%EC%A3%BC%EC%83%81%EB%AC%B4"
        } else if (teamName == "seongnam" || tName == "성남") {
            return "https://namu.wiki/w/%EC%84%B1%EB%82%A8%20FC?from=%EC%84%B1%EB%82%A8fc"
        } else if (teamName == "ulsan" || tName == "울산") {
            return "https://namu.wiki/w/%EC%9A%B8%EC%82%B0%20%ED%98%84%EB%8C%80?from=%EC%9A%B8%EC%82%B0%ED%98%84%EB%8C%80"
        }

        return ""
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
        teamLogo.setImageResource(
            baseContext.resources.getIdentifier(
                teamName,
                "drawable",
                baseContext.packageName
            )
        )

        teamLogo.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(findurl(teamName).toString())
            startActivity(i)
        }
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

