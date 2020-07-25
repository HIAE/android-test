package com.lcardoso.android_test.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JokeEntity::class], version = 1, exportSchema = false)
abstract class ChuckDatabase : RoomDatabase() {
    abstract fun jokeDAO(): JokeDAO
}