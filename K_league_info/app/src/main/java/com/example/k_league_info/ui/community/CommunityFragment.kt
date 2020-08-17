package com.example.k_league_info.ui.community

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k_league_info.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_community.*
import org.json.JSONObject

class CommunityFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_community, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var string = """
            { "community" : [
                {
                    "number" : 1,
                    "title" : "test1",
                    "content" : "내용",
                    "recommend" : "3",
                    "comment" : "3"
                },
                {
                    "number" : 2,
                    "title" : "test2",
                    "content" : "내용",
                    "recommend" : "3",
                    "comment" : "3"
                },
                {
                    "number" : 3,
                    "title" : "test3",
                    "content" : "내용",
                    "recommend" : "3",
                    "comment" : "3"
                },
                {
                    "number" : 4,
                    "title" : "test4",
                    "content" : "내용이 길어지면 내용이 길어지면 내용이 길이지면 내용이 길어지면",
                    "recommend" : "3000",
                    "comment" : "3000"
                },
            ]}
        """.trimIndent()
        //json 형식을 CommunityBoard 형식으로 파싱하여 boardList에 삽입
        var boardList = arrayListOf<CommunityBoard>()
        val gson = GsonBuilder().create()

        val jsonObject = JSONObject(string)
        val jsonArray = jsonObject.optJSONArray("community")

        for(i in 0 until jsonArray.length()-1) {
            var board = gson.fromJson(jsonArray.getJSONObject(i).toString(), CommunityBoard::class.java)
            boardList.add(board)
        }

        boardList.sortWith(Comparator { t, t2 -> t2.number.compareTo(t.number) })
        //구분선 추가
        val decoration = DividerItemDecoration(view.context, 1)
        CRecycler.addItemDecoration(decoration)

        val communityAdapter = activity?.let { CommunityAdapter(it, boardList) }
        CRecycler.adapter = communityAdapter

        val lm = LinearLayoutManager(activity)
        CRecycler.layoutManager = lm
        CRecycler.setHasFixedSize(true)

        //아래로 당겼을때 새로 고쳐지는 코드
        var num = 11
        refreshLayout.setOnRefreshListener {
            boardList.add(CommunityBoard(num++,"제목자리","내용자리","추천수", "댓글수"));
            boardList.sortWith(Comparator { t, t2 -> t2.number.compareTo(t.number) })
            CRecycler.adapter?.notifyDataSetChanged();
            refreshLayout.isRefreshing = false
        }

        write.setOnClickListener {
            val intent = Intent(context, PostWrite::class.java)
            startActivity(intent)
        }

    }
}