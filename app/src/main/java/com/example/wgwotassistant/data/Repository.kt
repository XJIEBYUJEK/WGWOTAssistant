package com.example.wgwotassistant.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class Repository(override val coroutineContext: CoroutineContext = Dispatchers.IO
) : CoroutineScope{
    private val api = Retrofit.Builder()
        .baseUrl(WgApi.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WgApi::class.java)

    fun getIdForNickname(nickname: String) = async {
        api.getIdForNickname(nickname).execute().body()
    }
}