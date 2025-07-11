package com.hannes.guitarbook.controllers

import com.hannes.guitarbook.dtos.SongDetailedDto
import com.hannes.guitarbook.dtos.SongDto
import com.hannes.guitarbook.services.SongService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class SongController(val songService: SongService) {

    @GetMapping("songs")
    fun getAllSongs(): List<SongDto> = songService.getAllSongs()

    @PostMapping("songs/add")
    fun addSong(@RequestBody songDetailedDto: SongDetailedDto) = songService.addSong(songDetailedDto)

    @GetMapping("songs/{id}")
    fun getSongById(@PathVariable id: Long): SongDetailedDto = songService.getSongById(id)

    @GetMapping("songs/search")
    fun searchSongs(@RequestParam query: String): List<SongDto> = songService.searchSong(query)

    @GetMapping("songs/by-artist/{artist}")
    fun getSongsByArtist(@PathVariable artist: String): List<SongDto> = songService.findSongByArtist(artist)

    @DeleteMapping("songs/{id}")
    fun deleteSongById(@PathVariable id: Long) = songService.deleteSongById(id)

}