package com.example.wgwotassistant.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WgApi {
    companion object{
        const val API_URL = "https://api.worldoftanks.ru/wot/account/list/"
        const val API_KEY = "2b8995defb77257803250b06a9783288"
    }
    @GET("?application_id=$API_KEY&limit=1")
    fun getIdForNickname(@Query("search") nickname: String): Call<WG>


}
