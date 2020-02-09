package com.example.wgwotassistant.data

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

fun apiReader(url: String) : String {
    var stringJson = ""
    val urlJson = URL(url)
    val reader = BufferedReader(
        InputStreamReader(
            urlJson.openStream()
        )
    )

    var inputLine: String?

    while (true){
        inputLine = reader.readLine()
        if (inputLine == null) break
        stringJson += inputLine
    }
    reader.close()
    return stringJson

}