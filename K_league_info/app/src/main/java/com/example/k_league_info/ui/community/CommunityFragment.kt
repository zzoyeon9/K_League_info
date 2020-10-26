package com.example.k_league_info.ui.community

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k_league_info.*
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.fragment_community.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class CommunityFragment : Fragment() {
    private fun refreshList() {
        AppData.restAPI.getCommunity().enqueue(object : Callback<JsonArray> {
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
            }
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                val gson = GsonBuilder().create()
                val jsonArray = JSONArray(response.body()!!.toString())
                for (i in 0 until jsonArray.length()) {
                    val board = gson.fromJson(
                        jsonArray.getJSONObject(i).toString(),
                        CommunityBoard::class.java
                    )
                    AppData.communityList.add(board)
                }
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
        return inflater.inflate(R.layout.fragment_community, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val communityAdapter = activity?.let { CommunityAdapter(it, AppData.communityList) }
        CRecycler.adapter = communityAdapter

        val lm = LinearLayoutManager(activity)
        CRecycler.layoutManager = lm
        CRecycler.setHasFixedSize(true)

        refreshLayout.setOnRefreshListener {
            AppData.communityList.clear()
            refreshList()
        }

        write.setOnClickListener {
            val intent = Intent(context, PostWrite::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (AppData.refreshData) {
            AppData.communityList.clear()
            refreshList()
            AppData.refreshData = false
        }
    }
}