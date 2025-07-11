import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hannes.guitarbookapp.enums.SongStatus
import com.hannes.guitarbookapp.models.SongPostDto
import com.hannes.guitarbookapp.viewmodel.SongViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSongScreen(
    navController: NavController,
    viewModel: SongViewModel = viewModel()
) {
    var title by remember { mutableStateOf("") }
    var artist by remember { mutableStateOf("") }
    var chordSheet by remember { mutableStateOf("") }
    var videoUrl by remember { mutableStateOf("") }
    var status by remember { mutableStateOf(SongStatus.LEARNING) }

    var error by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add song") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            //Text("Add new song", fontSize = 24.sp)

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )

            OutlinedTextField(
                value = artist,
                onValueChange = { artist = it },
                label = { Text("Artist") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )

            OutlinedTextField(
                value = chordSheet,
                onValueChange = { chordSheet = it },
                label = { Text("Chords + text") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                maxLines = 8
            )

            OutlinedTextField(
                value = videoUrl,
                onValueChange = { videoUrl = it },
                label = { Text("YouTube-URL") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )

            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Text("Status: ")
                DropdownMenuStatus(status, onChange = { status = it })
            }

            Button(
                onClick = {
                    if (title.isBlank() || artist.isBlank() || chordSheet.isBlank()) {
                        error = "You have to fill out the fields"
                        return@Button
                    }

                    val song = SongPostDto(
                        title = title,
                        artist = artist,
                        chordSheet = chordSheet,
                        videoUrl = videoUrl,
                        status = status
                    )

                    viewModel.addSong(
                        song = song,
                        onSuccess = { navController.popBackStack() },
                        onError = { error = it }
                    )
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Save song")
            }

            error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}


@Composable
fun DropdownMenuStatus(
    selected: SongStatus,
    onChange: (SongStatus) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(text = selected.name)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            SongStatus.entries.forEach { status ->
                DropdownMenuItem(
                    text = { Text(status.name) },
                    onClick = {
                        onChange(status)
                        expanded = false
                    }
                )
            }
        }
    }
}