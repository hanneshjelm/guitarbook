package com.hannes.guitarbookapp.models

import com.hannes.guitarbookapp.enums.SongStatus

data class SongPostDto(
    val title: String,
    val artist: String,
    val chordSheet: String,
    val videoUrl: String,
    val status: SongStatus
)
