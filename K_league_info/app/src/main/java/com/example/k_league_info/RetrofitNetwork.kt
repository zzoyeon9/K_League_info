package com.example.k_league_info

import com.example.k_league_info.ui.community.CommunityBoard
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface RetrofitNetwork {
    @GET("community") //API에서 사용하는 리소스 이름
    fun getPostList() : Call<JsonArray>

    @GET("community")
    fun getPost(@Query("number") number : Integer) : Call<JsonObject>

    @POST("community")
    fun setPost(@Body communityBoard: CommunityBoard) : Call<Void>

    @GET("/matchResult")
    fun getPost2() : Call<JsonArray>

    @GET("teamdetail")  // API에서 사용하는 리소스 이름
    fun getPostTeamdetail() : Call<JsonArray>

    @GET("playerdetail")  // API에서 사용하는 리소스 이름
    fun getPostPlayerdetail() : Call<JsonArray>

}
