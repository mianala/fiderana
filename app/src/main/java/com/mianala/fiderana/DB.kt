package com.mianala.fiderana

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Entity
data class Lyric(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "part") val part: String,
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
data class Author(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "name") val name: String,
)

@Entity
data class Song(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "structure") val structure: String,
    @ColumnInfo(name = "author_id") val authorId: Int,
    @ColumnInfo(name = "lowest") val lowest: String,
    @ColumnInfo(name = "highest") val highest: String,
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

    @Query(
        "SELECT * " +
                "FROM song, author " +
                "WHERE author.uid = song.author_id AND author.uid = :authorId"
    )
    fun getAuthorSongs(authorId:Int): Flow<List<Song>>
}

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: Category)

    @Query("SELECT * FROM category")
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

    @Query("SELECT * FROM author")
    fun getAll(): Flow<List<Author>>

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

@Database(entities = [Lyric::class, Song::class, Category::class, Author::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lyricDao(): LyricDao
    abstract fun songDao(): SongDao
    abstract fun categoryDao(): CategoryDao
    abstract fun authorDao(): AuthorDao

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