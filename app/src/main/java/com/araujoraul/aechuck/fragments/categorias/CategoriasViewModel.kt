package com.araujoraul.aechuck.fragments.categorias

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.araujoraul.aechuck.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriasViewModel : ViewModel() {

    private val repository = CategoriasRepository()

    val showCategories: LiveData<List<String>> = repository.showCategories
    val showProgressBar: LiveData<Boolean> = repository.showProgressBar
    val showMessageNoInternet: LiveData<Event<Boolean>> = repository.showMessageNoInternet
    val showMessageServerError: LiveData<Event<Boolean>> = repository.showMessageServerError

    fun taskCategories() = viewModelScope.launch(Dispatchers.IO) { repository.taskCategories() }

}