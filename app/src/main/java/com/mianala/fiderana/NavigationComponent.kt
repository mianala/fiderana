package com.mianala.fiderana

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "dial") {
        composable("dial") {
            DialScreen()
        }
        composable("song") {
            SongScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
        composable("category") {
            CategoryScreen()
        }
        composable("playlist") {
            PlaylistScreen()
        }
        composable("authors") {
            AuthorsScreen()
        }
        composable("songs") {
            SongsScreen()
        }
    }
}