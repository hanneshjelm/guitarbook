package com.hannes.guitarbook.services.impl

import com.hannes.guitarbook.dtos.SongDetailedDto
import com.hannes.guitarbook.dtos.SongDto
import com.hannes.guitarbook.models.Song
import com.hannes.guitarbook.repos.SongRepository
import com.hannes.guitarbook.services.SongService
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class SongServiceImpl(val songRepository: SongRepository): SongService {

    fun songToSongDto(song: Song): SongDto {
        return SongDto(
            id = song.id,
            title = song.title,
            artist = song.artist,
            status = song.status
        )
    }

    fun songToDetailedDto(song: Song): SongDetailedDto {
        return SongDetailedDto(
            id = song.id,
            title = song.title,
            artist = song.artist,
            chordSheet = song.chordSheet,
            videoUrl = song.videoUrl,
            status = song.status
        )
    }


    override fun getAllSongs(): List<SongDto> = songRepository.findAll().map(this::songToSongDto)

    override fun addSong(songDetailedDto: SongDetailedDto) {
        val song = Song(
            title = songDetailedDto.title,
            artist = songDetailedDto.artist,
            chordSheet = songDetailedDto.chordSheet,
            videoUrl = songDetailedDto.videoUrl,
            status = songDetailedDto.status,
        )
        songRepository.save(song)
    }

    override fun getSongById(id: Long): SongDetailedDto {
        val song = songRepository.findById(id).orElseThrow {
            EntityNotFoundException("Could not find a song with id $id.") }

        return songToDetailedDto(song)
    }

    override fun deleteSongById(id: Long) {
        if (!songRepository.existsById(id)) {
            throw EntityNotFoundException("Could not find a song with id $id.")
        }
        songRepository.deleteById(id)
    }

    override fun searchSong(query: String): List<SongDto> {
        return songRepository.searchSongByTitleContainingIgnoreCase(query)
            .map(this::songToSongDto)
    }

    override fun findSongByArtist(artist: String): List<SongDto> {
        return songRepository.findSongsByArtistContainingIgnoreCase(artist)
            .map(this::songToSongDto)
    }
}