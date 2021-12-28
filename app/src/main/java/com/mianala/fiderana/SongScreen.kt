package com.mianala.fiderana

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach

class LyricViewModel(application: Application) : AndroidViewModel(application) {
    val database by lazy { AppDatabase.getDatabase(application) }
    private val lyricDao = database.lyricDao()

    fun getLyric(id: Int): Flow<List<Lyric>> {
        return lyricDao.getSongLyrics(id)
    }
}

@Composable
fun SongScreen(
    navController: NavController,
    backStackEntry: NavBackStackEntry,
    lyricViewModel: LyricViewModel = viewModel(),
    songsViewModel: SongsViewModel = viewModel()
) {
    val songId = backStackEntry.arguments?.getString("songId") ?: return
    val song = songsViewModel.getSong(songId.toInt())
    val lyrics by lyricViewModel.getLyric(songId.toInt()).collectAsState(initial = emptyList())
    Column() {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text(text = song.title, style = MaterialTheme.typography.h3)
            }
            item {
                Text(text = "HN5 - T130", style = MaterialTheme.typography.body1)
            }

            items(lyrics.toList()) {
                Text(
                    text = it.content, fontSize = 24.sp
                )
            }
        }
    }
}