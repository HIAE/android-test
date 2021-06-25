package com.araujoraul.aechuck.fragments.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.araujoraul.aechuck.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {

    private val repository = CategoriesRepository()

    val showCategories: LiveData<List<String>> = repository.showCategories
    val showProgressBar: LiveData<Boolean> = repository.showProgressBar
    val showMessageNoInternet: LiveData<Event<Boolean>> = repository.showMessageNoInternet
    val showMessageServerError: LiveData<Event<Boolean>> = repository.showMessageServerError

    fun taskCategories() = viewModelScope.launch(Dispatchers.IO) { repository.taskCategories() }

}