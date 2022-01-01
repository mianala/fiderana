package com.mianala.fiderana

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mianala.fiderana.AppDatabase
import com.mianala.fiderana.PlaylistSong
import com.mianala.fiderana.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SongsViewModel(application: Application) : AndroidViewModel(application) {
    val database by lazy { AppDatabase.getDatabase(application) }
    val songDao = database.songDao()

    var filteredSongs: Flow<List<Song>> = songDao.getAll()
    var filteredSongbookSongs: Flow<List<Song>> = songDao.getAllSongbookSongs()

    fun search(key: String) {
        this.filteredSongs = songDao.searchSong(key)
    }

    fun getSong(key: Int): Song {
        return songDao.getSong(key)
    }

    //     Dial functions
    private val _inputFlow = MutableStateFlow<Int>(0)
    val inputFlow: StateFlow<Int> = _inputFlow

    //    Dial screen church
    fun type(num: Int) {
        val newNumber = (_inputFlow.value.toString() + num.toString()).toInt()
        if (newNumber > 999) return
        _inputFlow.value = newNumber

        this.filteredSongbookSongs = songDao.searchSongByNumber(newNumber)
    }

    fun reset() {
        _inputFlow.value = 0
    }
}