package com.araujoraul.aechuck.fragments.favorites

import androidx.lifecycle.ViewModel
import com.araujoraul.aechuck.MainApplication
import com.araujoraul.aechuck.db.AppDatabase

class FavoritesViewModel : ViewModel() {

    private val repository: FavoritesRepository
    private val app = MainApplication.getInstance()
    private val db = AppDatabase.getInstance(app.applicationContext)


    init {
        val favoritesDao = db.favoritesDao()
        repository = FavoritesRepository(favoritesDao)
    }

    fun getAllFavorites() = repository.getAllFavorites()

}