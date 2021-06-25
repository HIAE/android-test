package com.araujoraul.aechuck.fragments.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.araujoraul.aechuck.MainApplication
import com.araujoraul.aechuck.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {

    private val repository: FavoritesRepository
    private val app = MainApplication.getInstance()
    private val db = AppDatabase.getInstance(app.applicationContext)


    init {
        val favoritesDao = db.favoritesDao()
        repository = FavoritesRepository(favoritesDao)
    }

    fun getAllFavorites() = repository.getAllFavorites()
    fun removeFavorite(idFavorite: Int) = viewModelScope.launch(Dispatchers.IO) { repository.removeFavorite(idFavorite) }

}