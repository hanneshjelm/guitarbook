package com.hannes.guitarbookapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hannes.guitarbookapp.api.RetrofitInstance
import com.hannes.guitarbookapp.models.Song
import com.hannes.guitarbookapp.models.SongListItem
import com.hannes.guitarbookapp.models.SongPostDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.http.Query

class SongViewModel: ViewModel() {
    private val _songs = MutableStateFlow<List<SongListItem>>(emptyList())
    val songs: StateFlow<List<SongListItem>> = _songs.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun getSongs() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = RetrofitInstance.api.getSongs()
                _songs.value = response
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchSongs(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                _songs.value = RetrofitInstance.api.searchSongs(query)
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            }finally {
                _isLoading.value = false
            }
        }
    }

    fun addSong(song: SongPostDto, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.addSong(song)
                onSuccess()
            } catch (e: Exception) {
                onError("Error: ${e.message}")
            }
        }
    }
}