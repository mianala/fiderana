package com.mianala.fiderana

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


@Composable
@Preview
fun PlaylistScreen() {
    Column {
        var expanded by remember { mutableStateOf(false) }
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
                    Text("Popup content \nhere", Modifier.padding(24.dp))
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

                    Button(
                        modifier = Modifier
                            .size(64.dp)
                            .align(alignment = Alignment.CenterVertically),
                        onClick = {},
                        elevation = null,
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),

                        ) {
                        Icon(
                            imageVector = Icons.Filled.PlayCircle,
                            tint = MaterialTheme.colors.secondary,
                            contentDescription = "Play",
                            modifier = Modifier.size(32.dp)
                        )
                    }

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

