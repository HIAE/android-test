package com.renanparis.chuckjokes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.renanparis.chuckjokes.data.database.converter.ListStringConverter
import com.renanparis.chuckjokes.data.database.dao.JokeDao
import com.renanparis.chuckjokes.data.model.Joke

@Database(version = 1, entities = [Joke::class], exportSchema = false)
@TypeConverters(ListStringConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun jokeDao(): JokeDao
}