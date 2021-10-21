package com.mianala.fiderana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    }) { paddings ->
                    Box(modifier = Modifier.padding(paddings)) {

                        Navigation(navController = navController)
                    }
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
                selectedContentColor = MaterialTheme.colors.primaryVariant,
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
fun DialNumber(number: Number) {
    Button(
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),
        modifier = Modifier.size(64.dp),
        onClick = {}
    ) {
        Text(text = number.toString(), textAlign = TextAlign.Center, fontSize = 24.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DialScreen() {
    var dialInput by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Row(horizontalArrangement = Arrangement.Center) {

            OutlinedTextField(
                value = dialInput,
                onValueChange = { dialInput = it },
                label = { Text("Label") }
            )
        }
        Column() {
            Row(Modifier.padding(10.dp), Arrangement.spacedBy(10.dp)) {
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "3", fontSize = 48.sp)
                }
                Column() {
                    Text(text = "Feno Fiderana")
                    Text(text = "Key: G")
                }
                Button(onClick = { /*TODO*/ }, shape = CircleShape) {
                    Icon(
                        imageVector = Icons.Filled.PlaylistAdd,
                        contentDescription = "Add to Playlist"
                    )
                }
            }
            Row(Modifier.padding(10.dp), Arrangement.spacedBy(10.dp)) {
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "3", fontSize = 48.sp)
                }
                Column() {
                    Text(text = "Feno Fiderana")
                    Text(text = "Key: G")
                }
                Button(onClick = { /*TODO*/ }, shape = CircleShape) {
                    Icon(
                        imageVector = Icons.Filled.PlaylistAdd,
                        contentDescription = "Add to Playlist"
                    )
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                DialNumber(1)
                DialNumber(2)
                DialNumber(3)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {

                DialNumber(4)
                DialNumber(5)
                DialNumber(6)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {

                DialNumber(7)
                DialNumber(8)
                DialNumber(9)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                Column(
                    modifier = Modifier.size(76.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "HF", fontSize = 24.sp)
                }
                DialNumber(0)
                Column(
                    modifier = Modifier.size(64.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
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
