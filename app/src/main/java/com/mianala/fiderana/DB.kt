package com.mianala.fiderana

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Entity
data class Lyric(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "part") val part: String?,
    @ColumnInfo(name = "number") val number: Int?,
    @ColumnInfo(name = "song_id") val songId: Int,
)

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "icon") val icon: String?,
    @ColumnInfo(name = "color") val color: String?,
)

@Entity
data class Playlist(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "song_id") val songId: String,
    @ColumnInfo(name = "order") val order: Int,
    @ColumnInfo(name = "played") val played: Boolean,
)

@Entity
data class Author(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "name") val name: String,
)

val songBooks = listOf("HF", "FFPM", "FF", "Antema")

@Entity
data class Song(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "structure") val structure: String?,
    @ColumnInfo(name = "author_id") val authorId: Int?,
    @ColumnInfo(name = "number_in_songbook") val numberInSongbook: Int?,
    @ColumnInfo(name = "songbook_id") val songbookId: Int?,
    @ColumnInfo(name = "lowest") val lowest: String?,
    @ColumnInfo(name = "highest") val highest: String?,
    @ColumnInfo(name = "created_at") val created_at: String?,
    @ColumnInfo(name = "updated_at") val updated_at: String?,
    @ColumnInfo(name = "tempo") val tempo: Int?,
    @ColumnInfo(name = "tags") val tags: String?,
    @ColumnInfo(name = "added_by") val addedBy: String?,
    @ColumnInfo(name = "song_link") val link: String?,
)

@DatabaseView()
data class SongDetail(
    val title: String,
    val author: String,
)

@DatabaseView()
data class AuthorDetail(
    val title: String,
    val author: String,
)

@Dao
interface SongDao {
    @Insert
    fun insert(song: Song)

    @Query("SELECT * FROM song LIMIT 50")
    fun getAll(): Flow<List<Song>>

    @Query("SELECT * FROM song WHERE number_in_songbook IS NOT NULL LIMIT 50 ")
    fun getAllSongbookSongs(): Flow<List<Song>>

    @Query("SELECT * FROM song WHERE number_in_songbook LIKE :numberKey || '%' ORDER BY number_in_songbook")
    fun searchSongByNumber(numberKey: Int): Flow<List<Song>>

    @Query("SELECT * FROM song WHERE title LIKE '%' || :key|| '%'")
    fun searchSong(key: String): Flow<List<Song>>

    @Query("SELECT * FROM song WHERE uid IN (:songIds)")
    fun getAllByIds(songIds: IntArray): Flow<List<Song>>

    @Query("SELECT * FROM song WHERE song.uid = :authorId")
    fun getAuthorSongs(authorId: Int): Flow<List<Song>>

// TODO   why does this work? I tried and it works, shouldn't it return a list
    @Query("SELECT * FROM song WHERE song.uid = :songId")
    fun getSong(songId: Int): Song
}

@Dao
interface LyricDao{
    @Query("SELECT * FROM lyric WHERE song_id = :songId")
    fun getSongLyrics(songId: Int): Flow<List<Lyric>>
}

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: Category)

    @Query("SELECT * FROM category LIMIT 50")
    fun getAll(): Flow<List<Category>>

//    @Query(
//        "SELECT songcategory.songId " +
//                "FROM category, songcategory " +
//                "WHERE category.uid = songcategory.categoryId AND category.uid = :categoryId"
//    )
//    fun getCategorySongIds(categoryId:Int): Flow<List<Song>>
}

@Dao
interface AuthorDao {
    @Insert
    fun insert(author: Author)

    @Query("SELECT * FROM author LIMIT 50")
    fun getAll(): Flow<List<Author>>

}

@Dao
interface PlaylistDao {
    @Insert
    fun addSong(playlist: Playlist)

//    TODO updates the songs numbered bellow as played
    @Update
    fun setCurrentSong(playlist: Playlist)

    @Delete
    fun removeSong(playlist: Playlist)

    @Query("SELECT * FROM song WHERE uid IN (SELECT song_id FROM playlist)")
    fun getSongs(): Flow<List<Song>>

}

@Database(entities = [Lyric::class, Song::class, Category::class, Author::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lyricDao(): LyricDao
    abstract fun songDao(): SongDao
    abstract fun categoryDao(): CategoryDao
    abstract fun authorDao(): AuthorDao
    abstract fun playlistDao(): PlaylistDao

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
                    /*TODO remove allowMainThreadQueries*/
                ).allowMainThreadQueries()
//                    .createFromAsset("database/hf.db")
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}