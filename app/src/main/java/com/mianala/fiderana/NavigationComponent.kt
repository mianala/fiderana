package com.mianala.fiderana

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

object RoutesConstants {
    const val DIAL = "dial"
    const val SONG = "song"
    const val SETTINGS = "settings"
    const val CATEGORY = "category"
    const val PLAYLIST = "playlist"
    const val AUTHORS = "authors"
    const val SONGS = "songs"
}

@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = RoutesConstants.DIAL) {
        composable(RoutesConstants.DIAL) {
            DialScreen()
        }
        composable(RoutesConstants.SONG) {
            SongScreen()
        }
        composable(RoutesConstants.SETTINGS) {
            SettingsScreen()
        }
        composable(RoutesConstants.CATEGORY) {
            CategoryScreen()
        }
        composable(RoutesConstants.PLAYLIST) {
            PlaylistScreen()
        }
        composable(RoutesConstants.AUTHORS) {
            AuthorsScreen()
        }
        composable(RoutesConstants.SONGS) {
            SongsScreen()
        }
    }
}