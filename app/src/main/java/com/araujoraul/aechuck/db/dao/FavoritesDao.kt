package com.araujoraul.aechuck.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.araujoraul.aechuck.db.entities.FavoritesEntity

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM FavoritesEntity")
    fun getAllFavoritesFromDatabase() : LiveData<List<FavoritesEntity>>

    @Query("DELETE FROM FavoritesEntity WHERE id = :idFavorite")
    suspend fun deleteFavoriteByIdFromDatabase(idFavorite: Int)

    @Query("DELETE FROM FavoritesEntity")
    fun deleteAllFavoritesFromDatabase()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteIntoDatabase(favorite: FavoritesEntity)
}