package com.mianala.fiderana

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun DialNumber(n: Int,songsViewModel: SongsViewModel = viewModel()) {
    Button(
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = Modifier.size(64.dp),
        onClick = { songsViewModel.type(n) }
    ) {
        Text(
            text = n.toString(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
        )
    }
}

@Composable
fun DialTop(){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(0.dp, 10.dp)
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = null
            ) {
                Text(text = "HF", fontWeight = FontWeight.Black)
            }
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = null
            ) {
                Text(text = "FFPM", fontWeight = FontWeight.Light)
            }
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = null
            ) {
                Text(text = "FF", fontWeight = FontWeight.Light)
            }
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = null
            ) {
                Text(text = "Antema", fontWeight = FontWeight.Light)
            }
        }
        Button(
            onClick = { /*TODO*/ },
            shape = CircleShape,
            elevation = null,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
        ) {
            Icon(imageVector = Icons.Filled.MoreHoriz, contentDescription = "Add to playlist")
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun DialScreen(
    songsViewModel: SongsViewModel = viewModel(), navController: NavController
) {
    val dialInput by songsViewModel.inputFlow.collectAsState()
    val songs by songsViewModel.filteredSongbookSongs.collectAsState(initial = emptyList())


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DialTop()
        Column(
            modifier = Modifier
                .padding(0.dp, 20.dp)
                .weight(1.0f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Row(horizontalArrangement = Arrangement.Center) {
                Text(dialInput.toString(), style = MaterialTheme.typography.h4)
            }
            Column(
                Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .padding(0.dp, 24.dp)
            ) {

                LazyColumn(
                    Modifier
                        .padding(10.dp, 24.dp), verticalArrangement = Arrangement.SpaceAround
                ) {
                    // Primary songs
                    item {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = MaterialTheme.colors.primaryVariant,
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
                                    Text(text = "3", fontSize = 28.sp)
                                }
                                Column(
                                    Modifier
                                        .padding(12.dp, 0.dp)
                                        .weight(1f)
                                ) {
                                    Text(
                                        text = "Feno Fiderana",
                                        style = MaterialTheme.typography.subtitle1,
                                        fontWeight = FontWeight.Bold
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


                    // related songs
                    items(songs.toList()) {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            onClick = {
                                      navController.navigate(RoutesConstants.SONG+'/'+it.numberInSongbook.toString())
                            },
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(10.dp, 10.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,

                                ) {

                                Column(
                                    Modifier
                                        .padding(12.dp, 0.dp),
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
                                        style = MaterialTheme.typography.subtitle1,
                                        fontWeight = FontWeight.Bold,
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

                }
            }

            DialNumbers()

        }
    }
}

@Composable
fun DialNumbers(
    songsViewModel: SongsViewModel = viewModel(),
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
            DialNumber(
                1
            )
            DialNumber(
                2
            )
            DialNumber(
                3
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
            DialNumber(
                4
            )
            DialNumber(
                5
            )
            DialNumber(
                6
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
            DialNumber(
                7
            )
            DialNumber(
                8
            )
            DialNumber(
                9
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {

            Button(
                modifier = Modifier.size(64.dp),
                onClick = { songsViewModel.reset() },
                elevation = null,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Go",
                    modifier = Modifier.size(32.dp)
                )
            }
            DialNumber(
                0
            )
            Button(
                modifier = Modifier.size(64.dp),
                onClick = {},
                elevation = null,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    tint = MaterialTheme.colors.secondary,
                    contentDescription = "Go",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}
