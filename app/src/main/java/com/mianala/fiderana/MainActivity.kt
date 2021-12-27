package com.mianala.fiderana

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mianala.fiderana.ui.theme.FideranaTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database by lazy { AppDatabase.getDatabase(application) }
        val lyricDao = database.lyricDao()

        var lyric1 = Lyric(null, "Second content", "part", 3)
        var song1 = Song(null, "Second Song title", authorId = 3, highest = "A2", lowest = "C4", structure = "ABABB", tempo = 120, numberInSongbook = 1, songbookId = 1)
        var author1 = Author(null, name = "Second Author")
        var category1 = Category(null, title = "Second Category" , description = "Category Description", color = "RED", icon = "menu")
        database.lyricDao().insert(lyric1)
        database.categoryDao().insert(category1)
        database.authorDao().insert(author1)
        database.songDao().insert(song1)

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
                                    route = "songs",
                                    icon = Icons.Filled.Audiotrack
                                ),
                                NavItem(
                                    name = "Dial",
                                    route = "dial",
                                    icon = Icons.Filled.Pin
                                ),
                                NavItem(
                                    name = "Song",
                                    route = "song",
                                    icon = Icons.Filled.PlayCircle
                                ),
                                NavItem(
                                    name = "Category",
                                    route = "category",
                                    icon = Icons.Filled.EmojiPeople
                                ),
                                NavItem(
                                    name = "Playlist",
                                    route = "playlist",
                                    icon = Icons.Filled.PlaylistPlay
                                ),
                            ),
                            navController = navController,
                            onItemclick = { navController.navigate(it.route) }
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




class DialViewModel : ViewModel() {
    private val _inputFlow = MutableStateFlow<Int>(0)
    val inputFlow: StateFlow<Int> = _inputFlow

    fun type(num: Int) {
        val newNumber = (_inputFlow.value.toString() + num.toString()).toInt()
        if (newNumber > 999) return
        _inputFlow.value = newNumber
    }

    fun reset() {
        _inputFlow.value = 0
    }
}

class SongHeader(
    val id: Int = 0,
    val authorId: Int = 0,
    // verses are in numbers and choruses are in C Ex: V1-C-V2-C2-A-V3-C-B
    val arrangement: String = "",
    val key: String = "",
)

class PlaylistSong(var played: Boolean = false) {

    fun markAsPlayed() {
        played = true
    }
}

class CategoryViewModel(application: Application):AndroidViewModel(application) {
    val database by lazy { AppDatabase.getDatabase(application) }
    val categoryDao = database.categoryDao()
    val categories = categoryDao.getAll()
}

class AuthorViewModel(application: Application):AndroidViewModel(application) {
    val database by lazy { AppDatabase.getDatabase(application) }
    val authorDao = database.authorDao()
    val authors = authorDao.getAll()
}

class SongViewModel(application: Application) : AndroidViewModel(application) {
    val database by lazy { AppDatabase.getDatabase(application) }
    val songDao = database.songDao()

    //    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    private val _playlistSongs = MutableStateFlow<List<PlaylistSong>>(emptyList())

    //    private val _playingSong = MutableStateFlow<Song>()
    val songs: Flow<List<Song>> = songDao.getAll()

    val playlistSongs: StateFlow<List<PlaylistSong>> = _playlistSongs
//    val playingSong:StateFlow<Song> = new Song()

    fun sing(num: Int) {
    }

    fun search(num: Int) {
    }

    fun filter(num: Int) {
    }

    fun addToPlaylist(id: Int) {
        val songToAdd = PlaylistSong()
        val previousPlaylist = _playlistSongs.value
        _playlistSongs.value = previousPlaylist + songToAdd
    }

    fun markAsPlayed(id: Int) {

        val songToMarkAsPlayed = PlaylistSong()
//        playlistSongs.add(songToAdd)
    }

    fun removeFromPlaylist(id: Int) {
    }
}

class PlaylistViewModel : ViewModel() {
    private val _songs = MutableStateFlow<List<Song>>(emptyList())

    //    private val _playingSong = MutableStateFlow<Song>()
    val songs: StateFlow<List<Song>> = _songs
}
