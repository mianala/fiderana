package com.mianala.fiderana

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable


@Composable
fun SettingsScreen() {
    Column {
        Row {
            Button(onClick = {}) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
        Column {
            Row {
                Text(text = "Pejy Fanombohana")
            }
            Row {
                Text(text = "Fihirana Fanombohana")
            }
            Row {
                Text(text = "Fiverenana isan'andininy")
            }
        }
    }
}