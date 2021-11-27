package com.mianala.fiderana

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.*
import com.mianala.fiderana.ui.theme.FideranaTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database by lazy { AppDatabase.getDatabase(application) }
        val lyricDao = database.lyricDao()

        var lyric1 = Lyric(-1, "content", "part", 3)
        lyricDao.insert(lyric1)

        val allSongs = lyricDao.getAll()
        Log.d("tag", allSongs.toString())


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
                    Box(modifier = Modifier.padding(bottom = paddings.calculateBottomPadding())) {
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


@ExperimentalMaterialApi
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

class Dial : ViewModel() {
    private val _inputFlow = MutableStateFlow<Int>(0)
    val inputFlow: StateFlow<Int> = _inputFlow

    fun type(num: Int) {
        val newNumber = (_inputFlow.value.toString() + num.toString()).toInt()
        if (newNumber > 999) return
        _inputFlow.value = newNumber
    }

    fun reset() {
        _inputFlow.value = 0
    }
}

class SongHeader(
    val id: Int = 0,
    val authorId: Int = 0,
    // verses are in numbers and choruses are in C Ex: V1-C-V2-C2-A-V3-C-B
    val arrangement: String = "",
    val key: String = "",
)

class PlaylistSong(var played: Boolean = false) {

    fun markAsPlayed() {
        played = true
    }
}

class SongViewModel(application: Application) : AndroidViewModel(application) {
    val database by lazy { AppDatabase.getDatabase(application) }
    val lyricDao = database.lyricDao()


    //    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    private val _playlistSongs = MutableStateFlow<List<PlaylistSong>>(emptyList())

    //    private val _playingSong = MutableStateFlow<Song>()
    val songs: Flow<List<Lyric>> = lyricDao.getAll()

    val playlistSongs: StateFlow<List<PlaylistSong>> = _playlistSongs
//    val playingSong:StateFlow<Song> = new Song()

    fun sing(num: Int) {
    }

    fun search(num: Int) {
    }

    fun filter(num: Int) {
    }

    fun getSongsFromDatabase(num: Int) {

    }

    fun addToPlaylist(id: Int) {
        val songToAdd = PlaylistSong()
        val previousPlaylist = _playlistSongs.value
        _playlistSongs.value = previousPlaylist + songToAdd
    }

    fun markAsPlayed(id: Int) {

        val songToMarkAsPlayed = PlaylistSong()
//        playlistSongs.add(songToAdd)
    }

    fun removeFromPlaylist(id: Int) {
    }
}

class PlaylistViewModel : ViewModel() {
    private val _songs = MutableStateFlow<List<Song>>(emptyList())

    //    private val _playingSong = MutableStateFlow<Song>()
    val songs: StateFlow<List<Song>> = _songs
}

@Composable
fun DialNumber(n: Int, dialViewModel: Dial = viewModel()) {
    Button(
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = Modifier.size(64.dp),
        onClick = { dialViewModel.type(n) }
    ) {
        Text(
            text = n.toString(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
        )
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DialScreen(dialViewModel: Dial = viewModel()) {
    val dialInput by dialViewModel.inputFlow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

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
                    .verticalScroll(rememberScrollState())
                    .wrapContentHeight()
            ) {

                Column(
                    Modifier
                        .padding(32.dp, 24.dp), Arrangement.spacedBy(14.dp)
                ) {

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
                    for (i in 1..5) {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            onClick = {},
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
                                    Text(text = "385", fontSize = 28.sp)
                                }
                                Column(
                                    Modifier
                                        .padding(12.dp, 0.dp)
                                        .weight(1f)
                                ) {
                                    Text(
                                        text = "Injay Tompo o Ilay Feonao",
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
                        onClick = { dialViewModel.reset() },
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
    }
}

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


@ExperimentalMaterialApi
@Composable
fun SongsScreen() {
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
                onClick = { /*TODO*/ },
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
                for (i in 1..5) {
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
                                Text(text = "3", fontSize = 28.sp)
                            }
                            Column(
                                Modifier
                                    .padding(12.dp, 0.dp)
                                    .weight(1f)
                            ) {
                                Text(
                                    text = "Feno Fiderana",
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
                for (i in 1..5) {
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
                                    text = "Injay Tompo o Ilay Feonao",
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
                for (i in 1..5) {
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
                                    text = "Rija Rasolondraibe",
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

@Composable
fun SongScreen() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "Title", style = MaterialTheme.typography.h3)
            Text(text = "HN5 - T130", style = MaterialTheme.typography.body1)
            Text(
                text = "Tsy moramora ny mankatoa " +
                        "\n Manefy ny sitrapo " +
                        "\n Manda ny tena koa", fontSize = 24.sp
            )
            Text(
                text = "Tsy moramora ny mankatoa " +
                        "\n Manefy ny sitrapo " +
                        "\n Manda ny tena koa", fontSize = 24.sp
            )
            Text(
                text = "Tsy moramora ny mankatoa " +
                        "\n Manefy ny sitrapo " +
                        "\n Manda ny tena koa", fontSize = 24.sp
            )
            Text(
                text = "Tsy moramora ny mankatoa " +
                        "\n Manefy ny sitrapo " +
                        "\n Manda ny tena koa", fontSize = 24.sp
            )
            Text(
                text = "Tsy moramora ny mankatoa " +
                        "\n Manefy ny sitrapo " +
                        "\n Manda ny tena koa", fontSize = 24.sp
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CategoryScreen() {
    Column(Modifier.verticalScroll(rememberScrollState())) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp, 32.dp), Arrangement.spacedBy(16.dp)

        ) {
            for (i in 1..5) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.Cyan, shape = RoundedCornerShape(8.dp))
                        .height(IntrinsicSize.Min)
                        .clickable { }
                ) {
                    Image(
                        modifier = Modifier
                            .width(224.dp)
                            .align(alignment = Alignment.TopEnd)
                            .offset(y = -68.dp, x = 50.dp),
                        painter = painterResource(id = R.drawable.ic_pray),
                        contentDescription = null,
                    )
                    Box(modifier = Modifier.wrapContentHeight()) {

                        Column(
                            Modifier
                                .padding(24.dp)
                                .padding(end = 100.dp),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "Fiderana sy Fanandratana",
                                style = MaterialTheme.typography.h6,
                            )
                            Text(
                                text = "Hira 50",
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier
                                    .padding(0.dp, 10.dp)
                            )
                            Text(
                                text = "Fo midera an'Andriamanitra ary mifaly aminy",
                                style = MaterialTheme.typography.body2,
                            )

                        }
                    }

                }
            }
        }
    }
}


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

@Composable
fun AuthorsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Authors")
    }
}

@Entity
data class Lyric(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "part") val part: String,
    @ColumnInfo(name = "song_id") val songId: Int,
)

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "color") val color: String,
)

@Entity
data class SongCategory(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "songId") val songId: Int,
    @ColumnInfo(name = "categoryId") val categoryId: Int,
)

@Entity
data class Author(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") val name: String,
)


@Entity
data class Song(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "structure") val structure: String,
    @ColumnInfo(name = "author_id") val authorId: Int,
    @ColumnInfo(name = "lowest") val lowest: Int,
    @ColumnInfo(name = "highest") val highest: Int,
    @ColumnInfo(name = "tempo") val tempo: Int,
)

@Dao
interface SongDao {
    @Insert
    fun insert(song: Song)


    @Query("SELECT * FROM song")
    fun getAll(): Flow<List<Song>>

    @Query("SELECT * FROM song WHERE uid IN (:songIds)")
    fun getAllByIds(songIds:IntArray): Flow<List<Song>>
}

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: Category)

    @Query("SELECT * FROM category")
    fun getAll(): Flow<List<Category>>

    @Query(
        "SELECT songcategory.songId " +
                "FROM category, songcategory " +
                "WHERE category.uid = songcategory.categoryId AND category.uid = :categoryId"
    )
    fun getCagetorySongIds(categoryId:Int): Flow<List<Song>>
}

@Dao
interface LyricDao {
    @Query("SELECT * FROM lyric")
    fun getAll(): Flow<List<Lyric>>

    @Query("SELECT * FROM lyric WHERE song_id = :songId")
    fun findBySongId(songId: Int): List<Lyric>

    @Insert
    fun insertAll(vararg lyrics: Lyric)

    @Insert
    fun insert(lyric: Lyric)

    @Delete
    fun delete(lyric: Lyric)
}

@Database(entities = [Lyric::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lyricDao(): LyricDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "hira-fiderana-database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}