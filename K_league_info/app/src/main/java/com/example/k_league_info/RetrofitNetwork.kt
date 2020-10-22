package com.example.k_league_info

import com.example.k_league_info.ui.community.CommunityBoard
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface RetrofitNetwork {
    @GET("community")
    fun getCommunity() : Call<JsonArray>

    @POST("community")
    fun postCommunity(@Body community : CommunityBoard) : Call<Void>

    @HTTP(method = "DELETE", path = "community", hasBody = true)
    fun deleteCommunity(@Body number : JSONObject) : Call<ResponseBody>

    @GET("/matchResult")
    fun getPost2() : Call<JsonArray>

    @GET("teamdetail")  // API에서 사용하는 리소스 이름
    fun getPostTeamdetail() : Call<JsonArray>

    @GET("playerdetail")  // API에서 사용하는 리소스 이름
    fun getPostPlayerdetail() : Call<JsonArray>

}
