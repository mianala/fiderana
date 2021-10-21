package com.mianala.fiderana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mianala.fiderana.ui.theme.FideranaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FideranaTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            items = listOf(
                                NavItem(
                                    name = "Songs",
                                    route = "songs",
                                    icon = Icons.Filled.Audiotrack
                                ),
                                NavItem(
                                    name = "Dial",
                                    route = "dial",
                                    icon = Icons.Filled.Pin
                                ),
                                NavItem(
                                    name = "Song",
                                    route = "song",
                                    icon = Icons.Filled.PlayCircle
                                ),
                                NavItem(
                                    name = "Category",
                                    route = "category",
                                    icon = Icons.Filled.EmojiPeople
                                ),
                                NavItem(
                                    name = "Playlist",
                                    route = "playlist",
                                    icon = Icons.Filled.PlaylistPlay
                                ),
                            ),
                            navController = navController,
                            onItemclick = { navController.navigate(it.route) }
                        )
                    }) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun NavigationBar(
    items: List<NavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemclick: (NavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemclick(item) },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.DarkGray,
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name,
                        modifier = Modifier.size(32.dp)
                    )
                }
            )

        }
    }
}


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "dial") {
        composable("dial") {
            DialScreen()
        }
        composable("song") {
            SongScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
        composable("category") {
            CategoryScreen()
        }
        composable("playlist") {
            PlaylistScreen()
        }
        composable("authors") {
            AuthorsScreen()
        }
        composable("songs") {
            SongsScreen()
        }
    }
}

@Composable
fun DialScreen() {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
        Row() {
            Button(onClick = { /*TODO*/ }) {

                Text(text = "1")
            }
            Button(onClick = { /*TODO*/ }) {

                Text(text = "2")
            }
            Button(onClick = { /*TODO*/ }) {

                Text(text = "3")
            }
        }
        Row() {
            Button(onClick = { /*TODO*/ }) {

                Text(text = "4")
            }
            Button(onClick = { /*TODO*/ }) {

                Text(text = "5")
            }
            Button(onClick = { /*TODO*/ }) {

                Text(text = "6")
            }
        }
        Row() {
            Button(onClick = { /*TODO*/ }) {

                Text(text = "7")
            }
            Button(onClick = { /*TODO*/ }) {

                Text(text = "8")
            }
            Button(onClick = { /*TODO*/ }) {

                Text(text = "9")
            }
        }
        Row() {
            DropdownMenu(expanded = true, onDismissRequest = { /*TODO*/ }) {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Text(text = "HF")
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Text(text = "FFPM")
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Text(text = "FF")
                }
            }
            Button(onClick = { /*TODO*/ }) {

                Text(text = "2")
            }
            Button(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Check, contentDescription = "Go")
            }
        }
    }
}
// Components

@Preview(showBackground = true)
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

@Preview(showBackground = true)
@Composable
fun DialPreview() {
    FideranaTheme {
        DialScreen()
    }
}

@Composable
fun SongsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Songs")
    }
}

@Composable
fun SongScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Song")
    }
}

@Composable
fun PlaylistScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Playlist")
    }
}

@Composable
fun CategoryScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Category")
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Settings")
    }
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
