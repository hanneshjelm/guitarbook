package com.hannes.guitarbook.models

import com.hannes.guitarbook.enums.SongStatus
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Lob

@Entity
data class Song(
    @Id @GeneratedValue
    val id: Long? = null,
    val title: String,
    val artist: String,
    @Lob val chordSheet: String,
    val videoUrl: String,
    val status: SongStatus
)
