package com.example.k_league_info

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val url = "https://5mmopdbvlj.execute-api.ap-northeast-2.amazonaws.com/2020-08-11/"//API url
    private var instance : Retrofit? = null

    fun getInstance() : Retrofit {
        if(instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}