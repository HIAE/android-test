package com.araujoraul.aechuck.fragments.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.araujoraul.aechuck.MainApplication
import com.araujoraul.aechuck.db.AppDatabase
import com.araujoraul.aechuck.domain.model.ChuckJoke
import com.araujoraul.aechuck.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeViewModel : ViewModel() {

    private val repository: JokeRepository
    private val app = MainApplication.getInstance()
    private val db = AppDatabase.getInstance(app.applicationContext)


    init {
        val favoritesDao = db.favoritesDao()
        repository = JokeRepository(favoritesDao)
    }

    val favoritedError: LiveData<Event<Boolean>> = repository.favoritedError
    val favoriteHasSaved: LiveData<Event<Boolean>> = repository.favoriteHasSaved
    val showRandomJoke: LiveData<ChuckJoke> = repository.showRandomJoke
    val showProgressBar: LiveData<Boolean> = repository.showProgressBar
    val showMessageNoInternet: LiveData<Event<Boolean>> = repository.showMessageNoInternet
    val showMessageServerError: LiveData<Event<Boolean>> = repository.showMessageServerError


    fun taskRandomJokeByCategory(category: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.taskRandomJokeByCategory(category) }

    fun saveJokeToFavorites(joke: String, category: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveJokeToFavorites(joke, category)
    }

}