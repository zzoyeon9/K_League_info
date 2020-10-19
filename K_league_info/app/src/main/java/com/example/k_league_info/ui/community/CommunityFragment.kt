package com.example.k_league_info.ui.community

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k_league_info.MyViewModel
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
import java.util.*
import kotlin.collections.ArrayList

class CommunityFragment : Fragment() {
    private var boardList = ArrayList<CommunityBoard>()
    private var retrofitClient = RetrofitClient()
    private val retrofit = retrofitClient.getInstance()
    private val api: RetrofitNetwork = retrofit.create(RetrofitNetwork::class.java)
    private val model : MyViewModel by activityViewModels()
    private fun getPost() {
        //비동기
        api.getPostList().enqueue(object : Callback<JsonArray> {
            //서버와 접속 실패
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
            }
            //서버와 접속 성공
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                val gson = GsonBuilder().create()
                //json 형식을 CommunityBoard 형식으로 파싱하여 boardList에 삽입
                val jsonArray = JSONArray(response.body()!!.toString())
                for (i in 0 until jsonArray.length()) {
                    val board = gson.fromJson(
                        jsonArray.getJSONObject(i).toString(),
                        CommunityBoard::class.java
                    )
                    boardList.add(board)
                }
                model.setBoardList(boardList.clone() as ArrayList<CommunityBoard>)
                CRecycler.adapter?.notifyDataSetChanged()
                refreshLayout.isRefreshing = false
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (model.getBoardList() == null)
            getPost()
        else
            boardList = model.getBoardList()!!


        return inflater.inflate(R.layout.fragment_community, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val communityAdapter = activity?.let { CommunityAdapter(it, boardList) }
        CRecycler.adapter = communityAdapter

        val lm = LinearLayoutManager(activity)
        CRecycler.layoutManager = lm
        CRecycler.setHasFixedSize(true)

        refreshLayout.setOnRefreshListener {
            boardList.clear()
            getPost()
        }

        write.setOnClickListener {
            val intent = Intent(context, PostWrite::class.java)
            startActivity(intent)
        }
    }
}