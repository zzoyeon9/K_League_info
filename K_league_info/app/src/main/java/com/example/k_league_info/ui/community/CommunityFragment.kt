package com.example.k_league_info.ui.community

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k_league_info.R
import com.example.k_league_info.RetrofitClient
import com.example.k_league_info.RetrofitNetwork
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.fragment_community.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityFragment : Fragment() {
    private var boardList = arrayListOf<CommunityBoard>()
    private var retrofitClient = RetrofitClient()
    private val retrofit = retrofitClient.instance
    private val api: RetrofitNetwork = retrofit.create(
        RetrofitNetwork::class.java)

    private fun PostList() {
        //비동기
        Runnable {
            api.getPost().enqueue(object : Callback<JsonArray>{
                //서버와 접속 실패
                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    Log.d("log",t.message)
                }
                //서버와 접속 성공
                override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                    val gson = GsonBuilder().create()
                    //json 형식을 CommunityBoard 형식으로 파싱하여 boardList에 삽입
                    val jsonArray = JSONArray(response.body()!!.toString())
                    for (i in 0 until jsonArray.length()) {
                        var board = gson.fromJson(jsonArray.getJSONObject(i).toString(), CommunityBoard::class.java)
                        boardList.add(board)
                    }
                    CRecycler.adapter?.notifyDataSetChanged();
                }
            })
        }.run()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        PostList()
        return inflater.inflate(R.layout.fragment_community, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val decoration = DividerItemDecoration(context, 1)
        CRecycler.addItemDecoration(decoration)

        val communityAdapter = activity?.let { CommunityAdapter(it, boardList) }
        CRecycler.adapter = communityAdapter

        val lm = LinearLayoutManager(activity)
        CRecycler.layoutManager = lm
        CRecycler.setHasFixedSize(true)

        //아래로 당겼을때 새로 고쳐지는 코드
        refreshLayout.setOnRefreshListener {
            boardList.clear()
            PostList()
            refreshLayout.isRefreshing = false
        }

        write.setOnClickListener {
            val intent = Intent(context, PostWrite::class.java)
            startActivity(intent)
        }
    }
}