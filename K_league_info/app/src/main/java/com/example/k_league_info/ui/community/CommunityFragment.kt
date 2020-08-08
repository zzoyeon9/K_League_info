package com.example.k_league_info.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k_league_info.R
import kotlinx.android.synthetic.main.fragment_community.*

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

        var boardList = arrayListOf<CommunityBoard>(
            CommunityBoard(1,"제목자리","내용자리","추천수", "댓글수"),
            CommunityBoard(2,"제목자리","내용자리","추천수", "댓글수"),
            CommunityBoard(3,"제목자리","내용자리","추천수", "댓글수"),
            CommunityBoard(4,"제목자리","내용자리","추천수", "댓글수"),
            CommunityBoard(5,"제목자리","내용자리","추천수", "댓글수"),
            CommunityBoard(6,"제목자리","내용자리","추천수", "댓글수"),
            CommunityBoard(7,"제목자리","내용자리","추천수", "댓글수"),
            CommunityBoard(8,"제목자리","내용자리","추천수", "댓글수"),
            CommunityBoard(9,"제목자리","내용자리","추천수", "댓글수"),
            CommunityBoard(10,"제목자리","내용자리","추천수", "댓글수")
        )
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

    }
}