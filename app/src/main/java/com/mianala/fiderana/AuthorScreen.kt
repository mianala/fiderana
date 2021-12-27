package com.mianala.fiderana

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.AndroidViewModel

@Composable
fun AuthorComponent() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box() {
            Text(text = "RI")
        }
        Column {
            Text("Rija Rasolondraibe")
            Text("Hira 40")
        }
    }
}


class AuthorViewModel(application: Application): AndroidViewModel(application) {
    val database by lazy { AppDatabase.getDatabase(application) }
    val authorDao = database.authorDao()
    val authors = authorDao.getAll()
}

@Composable
fun AuthorsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Authors")
    }
}