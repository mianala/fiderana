package com.mianala.fiderana

import android.app.Application
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import java.util.prefs.Preferences


object Settings {
    const val FONT_SIZE_KEY = "font_size_key"
    const val FONT_SIZE = 18
    const val PADDING = 18
    const val FONT_SIZE_INCREASE_VALUE = 4
}

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

}

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