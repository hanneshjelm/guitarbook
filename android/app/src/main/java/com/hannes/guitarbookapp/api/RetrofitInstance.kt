package com.hannes.guitarbookapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val baseurl = "http://10.0.2.2:8080/"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SongApi by lazy {
        getInstance().create(SongApi::class.java)
    }
}