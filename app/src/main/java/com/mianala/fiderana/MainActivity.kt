package com.mianala.fiderana

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.mianala.fiderana.ui.theme.FideranaTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database by lazy { AppDatabase.getDatabase(application) }
        val lyricDao = database.lyricDao()
//
//        var lyric1 = Lyric(null, "Second content", "part", 3)
//        var song1 = Song(null, "Second Song title", authorId = 3, highest = "A2", lowest = "C4", structure = "ABABB", tempo = 120, numberInSongbook = 1, songbookId = 1)
//        var author1 = Author(null, name = "Second Author")
//        var category1 = Category(null, title = "Second Category" , description = "Category Description", color = "RED", icon = "menu")
//        database.lyricDao().insert(lyric1)
//        database.categoryDao().insert(category1)
//        database.authorDao().insert(author1)
//        database.songDao().insert(song1)

        setContent {
            FideranaTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            items = listOf(
                                NavItem(
                                    name = "Songs",
                                    route = Routes.SONGS,
                                    icon = Icons.Filled.Audiotrack
                                ),
                                NavItem(
                                    name = "Dial",
                                    route = Routes.DIAL,
                                    icon = Icons.Filled.Pin
                                ),
                                NavItem(
                                    name = "Song",
//                                    TODO replace with stored last song
                                    route = Routes.SONG+"/1",
                                    icon = Icons.Filled.PlayCircle
                                ),
                                NavItem(
                                    name = "Category",
                                    route = Routes.CATEGORY,
                                    icon = Icons.Filled.EmojiPeople
                                ),
                                NavItem(
                                    name = "Playlist",
                                    route = Routes.PLAYLIST,
                                    icon = Icons.Filled.PlaylistPlay
                                ),
                            ),
                            navController = navController,
                            onItemclick = { navController.navigate(it.route){
                                popUpTo(Routes.DIAL) { inclusive = true }
                                launchSingleTop = true
                            } }
                        )
                    }) { paddings ->
                    Box(modifier = Modifier.padding(bottom = paddings.calculateBottomPadding())) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }
}

val Context.dataStore by preferencesDataStore(name = "settings")
