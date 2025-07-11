package com.hannes.guitarbookapp.models

import com.hannes.guitarbookapp.enums.SongStatus

data class SongListItem(
    val id: Long,
    val title: String,
    val artist: String,
    val status: SongStatus
)
