package com.hannes.guitarbookapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hannes.guitarbookapp.Routes
import com.hannes.guitarbookapp.models.Song
import com.hannes.guitarbookapp.models.SongListItem
import com.hannes.guitarbookapp.viewmodel.SongViewModel

@Composable
fun SongListScreen(viewModel: SongViewModel = viewModel(), navController: NavController) {
    val songs by viewModel.songs.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getSongs()
    }

    var title by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Guitar Book",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = title,
                onValueChange = {
                    title = it
                },
                label = {
                    Text(text = "Search for a song")
                })
            IconButton(onClick = {
                viewModel.searchSongs(title)
            }) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "Search for a song")
            }
            Button(onClick = {
                navController.navigate(Routes.addSongScreen)
            }) {
                Text(text = "Add Song")
            }
        }
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
            error != null -> {
                Text(
                    text = error!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
            else -> {
                LazyColumn {
                   items(songs) { currentSong ->
                       SongItem(song = currentSong, navController)
                   }
                }
            }
        }
    }
}

@Composable
fun SongItem(song: SongListItem, navController: NavController) {
    Card(
        onClick = {
            navController.navigate(Routes.songDetailedScreen + "/${song.id}")
        },
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = song.title,
                fontSize = 20.sp,
            )
            Text(
                text = song.artist,
                fontSize = 16.sp,
            )
            Text(
                text = "Status: ${song.status}",
                fontSize = 16.sp,
            )
        }
    }
}