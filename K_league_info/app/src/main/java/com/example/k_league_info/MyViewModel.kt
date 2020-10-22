package com.example.k_league_info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k_league_info.ui.community.CommunityBoard

class MyViewModel : ViewModel(){
    private var communityList = MutableLiveData<ArrayList<CommunityBoard>>()

    fun setCommunity(board : ArrayList<CommunityBoard>) {
        communityList.value = board
    }

    fun getCommunity(): ArrayList<CommunityBoard>? {
        return communityList.value
    }

}