package com.renanparis.chuckjokes.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.renanparis.chuckjokes.data.model.Joke

@Dao
interface JokeDao {

    @Query("SELECT * FROM Joke ORDER BY categories COLLATE NOCASE")
    suspend fun getJokes(): List<Joke>

    @Insert(onConflict = REPLACE)
    suspend fun saveJoke(joke: Joke)

    @Delete
    suspend fun removeJoke(joke: Joke)
}