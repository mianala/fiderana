package com.mianala.fiderana

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@ExperimentalMaterialApi
@Composable
fun SongsScreen(
    songsViewModel: SongsViewModel = viewModel(),
    authorViewModel: AuthorViewModel = viewModel(),
    navController: NavController
) {
    val songs by songsViewModel.filteredSongs.collectAsState(initial = emptyList())
    val authors by authorViewModel.authors.collectAsState(initial = emptyList())

    Column() {
        Column(Modifier.padding(10.dp, 0.dp)) {
            Row(
                modifier = Modifier
                    .padding(10.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var textFilter by remember { mutableStateOf("") }
                OutlinedTextField(value = textFilter, leadingIcon = { Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )}, onValueChange = {
                    textFilter = it
                    songsViewModel.search(it)
                },
                    label = { Text("Hira") })
                Button(
                    onClick = { },
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
            LazyColumn(
                Modifier
                    .wrapContentHeight()
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {

                items(songs.toList()) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        onClick = {
                             navController.navigate(RoutesConstants.SONG+'/'+it.uid.toString())
                        },
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(10.dp, 10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if(it.numberInSongbook != null){
                                Column(
                                    Modifier.padding(12.dp, 0.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = it.numberInSongbook.toString(), fontSize = 28.sp)
                                }
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

                //                authors
//                items(authors.toList()) {
//                    Card(
//                        shape = RoundedCornerShape(8.dp),
//                        onClick = {},
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .padding(10.dp, 10.dp)
//                                .fillMaxWidth(),
//                            verticalAlignment = Alignment.CenterVertically,
//                        ) {
//
//                            Column(Modifier.padding(12.dp, 0.dp)) {
//                                Text(
//                                    text = it.name,
//                                    style = MaterialTheme.typography.body1,
//                                )
//                                Text(text = "Hira 59", style = MaterialTheme.typography.caption)
//                            }
//                        }
//                    }
//                }

            }
        }
    }

}
