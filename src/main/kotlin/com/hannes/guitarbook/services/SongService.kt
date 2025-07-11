package com.hannes.guitarbook.services

import com.hannes.guitarbook.dtos.SongDetailedDto
import com.hannes.guitarbook.dtos.SongDto

interface SongService {
    fun getAllSongs(): List<SongDto>
    fun addSong(songDetailedDto: SongDetailedDto)
    fun getSongById(id: Long): SongDetailedDto
    fun deleteSongById(id: Long)
    fun searchSong(query: String): List<SongDto>
    fun findSongByArtist(artist: String): List<SongDto>
}