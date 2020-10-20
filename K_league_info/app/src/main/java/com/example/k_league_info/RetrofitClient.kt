package com.example.k_league_info

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val url =
        "https://5mmopdbvlj.execute-api.ap-northeast-2.amazonaws.com/2020-09-27/"//API url
    var instance = null
    fun getInstance(): Retrofit {
        var instance = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = instance.create(RetrofitNetwork::class.java)
        return instance
    }
}
