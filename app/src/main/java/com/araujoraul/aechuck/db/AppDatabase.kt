package com.araujoraul.aechuck.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.araujoraul.aechuck.db.dao.FavoritesDao
import com.araujoraul.aechuck.db.entities.FavoritesEntity

@Database(
    entities = [FavoritesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoritesDao() : FavoritesDao

    companion object {

       private const val DB_NAME = "chuckjokes.db"

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

}