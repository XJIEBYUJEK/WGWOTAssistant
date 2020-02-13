package com.example.wgwotassistant.data

data class WG(

    val data: List<Data>,
    val status: String

)
data class Data(
    val nickname: String,
    val account_id: Int
)
