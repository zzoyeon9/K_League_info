package com.example.k_league_info

import com.google.gson.JsonArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitNetwork {
    @GET("community") //API에서 사용하는 리소스 이름
    fun getPost() : Call<JsonArray>

    @FormUrlEncoded
    @POST("community")
    fun setPost(@Field("title") title: JSONObject) : Call<JSONObject>

    @GET("score") //API에서 사용하는 리소스 이름
    fun getPost2() : Call<JsonArray>

    @FormUrlEncoded
    @POST("score")
    fun setPost2(@Field("title") title: JSONObject) : Call<JSONObject>
}