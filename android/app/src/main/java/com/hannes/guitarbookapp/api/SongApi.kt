package com.hannes.guitarbookapp.api

import com.hannes.guitarbookapp.models.Song
import com.hannes.guitarbookapp.models.SongListItem
import com.hannes.guitarbookapp.models.SongPostDto
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SongApi {
    @GET("/songs")
    suspend fun getSongs(): List<SongListItem>

    @GET("/songs/{id}")
    suspend fun getSongById(@Path("id")id: Long): Song

    @GET("/songs/search")
    suspend fun searchSongs(@Query("query") query: String): List<SongListItem>

    @POST("/songs/add")
    suspend fun addSong(@Body song: SongPostDto)

}