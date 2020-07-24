package com.lcardoso.android_test.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lcardoso.android_test.data.model.JokeVO

@Database(entities = [JokeVO::class], version = 1, exportSchema = false)
abstract class ChuckDatabase : RoomDatabase() {
    abstract fun jokeDAO(): JokeDAO
}