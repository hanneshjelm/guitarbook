package com.hannes.guitarbookapp

import AddSongScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hannes.guitarbookapp.screens.*
import com.hannes.guitarbookapp.ui.theme.GuitarBookAppTheme
import com.hannes.guitarbookapp.viewmodel.SongViewModel
import androidx.compose.material3.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuitarBookAppTheme {
                val navController = rememberNavController()
                val sharedViewModel: SongViewModel = viewModel()
                NavHost(navController = navController, startDestination = Routes.songListScreen, builder = {
                    composable(Routes.songListScreen) {
                        SongListScreen(viewModel = sharedViewModel, navController)
                    }
                    composable(Routes.songDetailedScreen + "/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")?.toLongOrNull()
                        if (id != null) {
                            SongDetailedScreen(id, navController, sharedViewModel)
                        }
                    }
                    composable(Routes.addSongScreen) {
                        AddSongScreen(navController)
                    }
                })
            }
        }
    }
}
