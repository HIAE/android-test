package com.araujoraul.aechuck.fragments.piada

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.araujoraul.aechuck.domain.model.ChuckJoke
import com.araujoraul.aechuck.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PiadaViewModel : ViewModel() {

    private val repository = PiadaRepository()

    val showRandomJoke: LiveData<ChuckJoke> = repository.showRandomJoke
    val showProgressBar: LiveData<Boolean> = repository.showProgressBar
    val showMessageNoInternet: LiveData<Event<Boolean>> = repository.showMessageNoInternet
    val showMessageServerError: LiveData<Event<Boolean>> = repository.showMessageServerError

    fun taskRandomJokeByCategory(category: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.taskRandomJokeByCategory(category) }

}