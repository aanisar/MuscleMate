package com.lh1110642.gymgenie

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/exercises")
    fun getExercises() :Call<MutableList<PostModel>>
}