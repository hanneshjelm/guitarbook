package com.hannes.guitarbook.dtos

import com.hannes.guitarbook.enums.SongStatus
import jakarta.persistence.Lob

data class SongDetailedDto(
    val id: Long?,
    val title: String,
    val artist: String,
    @Lob val chordSheet: String,
    val videoUrl: String,
    val status: SongStatus
)
