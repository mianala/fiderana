package com.mianala.fiderana

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class PlaylistSong(var played: Boolean = false) {

    fun markAsPlayed() {
        played = true
    }
}

class PlaylistViewModel(application: Application) : AndroidViewModel(application) {
    val database by lazy { AppDatabase.getDatabase(application) }
    val playlistDao = database.playlistDao()

    private val _songs = MutableStateFlow<List<Song>>(emptyList())

    //    private val _playingSong = MutableStateFlow<Song>()
    val songs: StateFlow<List<Song>> = _songs
}


// it would be so cool if we could share the playlists with qr codes. for worshipers. for musicians
@Composable
@Preview
fun PlaylistScreen() {
    Column {
        var expanded by remember { mutableStateOf(false) }

//        menu
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {

                Button(
                    onClick = { expanded = !expanded },
                    shape = CircleShape,
                    elevation = null,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreHoriz,
                        contentDescription = "Add to playlist"
                    )
                }

                DropdownMenu(expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    Button(
                        onClick = { expanded = !expanded },
                        shape = CircleShape,
                        elevation = null,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreHoriz,
                            contentDescription = "Clear"
                        )
                    }
                }
            }


        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            for (i in 1..5) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        modifier = Modifier
                            .size(64.dp)
                            .align(alignment = Alignment.CenterVertically),
                        onClick = {},
                        elevation = null,
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.DragHandle,
                            tint = MaterialTheme.colors.secondary,
                            contentDescription = "Go",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Column(
                        Modifier
                            .padding(0.dp, 0.dp)
                            .weight(1f), verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Feno Fiderana",
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "Key: G", style = MaterialTheme.typography.caption)
                    }

//replaced

                    Button(
                        modifier = Modifier
                            .size(64.dp)
                            .align(alignment = Alignment.CenterVertically),
                        onClick = {},
                        elevation = null,
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.RemoveCircle,
                            tint = MaterialTheme.colors.secondary,
                            contentDescription = "Go",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
    }
}

