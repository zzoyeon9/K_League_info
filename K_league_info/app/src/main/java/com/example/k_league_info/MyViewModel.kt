package com.example.k_league_info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k_league_info.ui.community.CommunityBoard

class MyViewModel : ViewModel(){
    private var boardList = MutableLiveData<ArrayList<CommunityBoard>>()

    fun setBoardList(board : ArrayList<CommunityBoard>) {
        boardList.value = board
    }

    fun getBoardList(): ArrayList<CommunityBoard>? {
        return boardList.value
    }



}