package com.araujoraul.aechuck.fragments.favorites

import com.araujoraul.aechuck.db.dao.FavoritesDao

class FavoritesRepository(
    private var favoritesDao: FavoritesDao
) {

    fun getAllFavorites() = favoritesDao.getAllFavoritesFromDatabase()
    suspend fun removeFavorite(idFavorite: Int) = favoritesDao.deleteFavoriteByIdFromDatabase(idFavorite)

}