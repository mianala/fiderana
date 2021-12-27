package com.mianala.fiderana

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
import androidx.lifecycle.viewmodel.compose.viewModel


@ExperimentalMaterialApi
@Composable
fun SongsScreen(songViewModel:SongViewModel = viewModel(), authorViewModel: AuthorViewModel = viewModel()) {
    val songs by songViewModel.songs.collectAsState(initial = emptyList())
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