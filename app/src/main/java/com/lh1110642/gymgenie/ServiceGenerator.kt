package com.lh1110642.gymgenie

import okhttp3.OkHttpClient
import retrofit2.Retrofit

object  ServiceGenerator {
    var muscle = ""
    var diff = ""
    var type = ""
    var equipment =""

    var  APIMod = ""


    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://exercises-by-api-ninjas.p.rapidapi.com/v1/exercises")




}