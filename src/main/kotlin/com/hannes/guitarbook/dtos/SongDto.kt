package com.hannes.guitarbook.dtos

import com.hannes.guitarbook.enums.SongStatus

data class SongDto(
    val id: Long?,
    val title: String,
    val artist: String,
    val status: SongStatus
)
