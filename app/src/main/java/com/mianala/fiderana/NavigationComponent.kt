package com.mianala.fiderana

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

object Routes {
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
    NavHost(navController = navController, startDestination = Routes.DIAL) {
//        put navController inside model
        composable(Routes.DIAL) {
            DialScreen(navController = navController)
        }
        composable(Routes.SONG + "/{songId}") {
            SongScreen(navController = navController, backStackEntry = it)
        }
        composable(Routes.SETTINGS) {
            SettingsScreen()
        }
        composable(Routes.CATEGORY) {
            CategoryScreen(navController = navController)
        }
        composable(Routes.PLAYLIST) {
            PlaylistScreen()
        }
        composable(Routes.AUTHORS) {
            AuthorsScreen()
        }
        composable(Routes.SONGS) {
            SongsScreen(navController = navController)
        }
    }
}