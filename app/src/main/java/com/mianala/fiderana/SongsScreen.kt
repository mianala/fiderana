package com.mianala.fiderana

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class SongsViewModel(application: Application) : AndroidViewModel(application) {
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


@ExperimentalMaterialApi
@Composable
fun SongsScreen(songsViewModel:SongsViewModel = viewModel(), authorViewModel: AuthorViewModel = viewModel()) {
    val songs by songsViewModel.songs.collectAsState(initial = emptyList())
    val authors by authorViewModel.authors.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(value = "", onValueChange = {},
                label = { Text("Hira") })
            Button(
                onClick = {  },
                shape = CircleShape,
                elevation = null,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreHoriz,
                    contentDescription = "Add to playlist"
                )
            }
        }
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .wrapContentHeight()
        ) {
            Column(
                Modifier
                    .padding(24.dp, 10.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),

                ) {
                songs.forEach{
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        onClick = {},
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(10.dp, 10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column(
                                Modifier.padding(12.dp, 0.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = it.numberInSongbook.toString(), fontSize = 28.sp)
                            }
                            Column(
                                Modifier
                                    .padding(12.dp, 0.dp)
                                    .weight(1f)
                            ) {
                                Text(
                                    text = it.title,
                                    style = MaterialTheme.typography.body1,
                                )
                                Text(text = "Key: G", style = MaterialTheme.typography.caption)
                            }
                            Button(
                                onClick = { /*TODO*/ },
                                elevation = null,
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.PlaylistAdd,
                                    contentDescription = "Add to Playlist"
                                )
                            }
                        }
                    }
                }
                songs.forEach{
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        onClick = {},
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(10.dp, 10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column(Modifier.padding(12.dp, 0.dp)) {
                                Text(
                                    text = it.title,
                                    style = MaterialTheme.typography.body1,
                                )
                                Text(
                                    text = "Rija Rasolondraibe",
                                    style = MaterialTheme.typography.caption
                                )
                            }
                            Button(
                                onClick = { /*TODO*/ },
                                elevation = null,
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.PlaylistAdd,
                                    contentDescription = "Add to Playlist"
                                )
                            }
                        }
                    }
                }
                authors.forEach {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        onClick = {},
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(10.dp, 10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                            Column(Modifier.padding(12.dp, 0.dp)) {
                                Text(
                                    text = it.name,
                                    style = MaterialTheme.typography.body1,
                                )
                                Text(text = "Hira 59", style = MaterialTheme.typography.caption)
                            }
                        }
                    }
                }
            }
        }

    }
}