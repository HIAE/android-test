package com.renanparis.chuckjokes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.renanparis.chuckjokes.data.database.dao.JokeDao
import com.renanparis.chuckjokes.data.model.Joke

@Database(version = 1, entities = [Joke::class], exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun jokeDao(): JokeDao
}