package com.hannes.guitarbook.repos

import com.hannes.guitarbook.dtos.SongDto
import com.hannes.guitarbook.models.Song
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.PathVariable

interface SongRepository:JpaRepository<Song, Long> {
    fun searchSongByTitleContainingIgnoreCase(title: String): List<Song>
    fun findSongsByArtistContainingIgnoreCase(artist: String): List<Song>
}