package com.lcardoso.android_test.ui.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lcardoso.android_test.BaseViewModel
import com.lcardoso.android_test.data.StateError
import com.lcardoso.android_test.data.StateLoading
import com.lcardoso.android_test.data.StateResponse
import com.lcardoso.android_test.data.StateSuccess
import com.lcardoso.android_test.data.model.JokeVO
import com.lcardoso.android_test.usecase.FetchJokesUseCase

class JokeViewModel(
    private val fetchJokesUseCase: FetchJokesUseCase
) : BaseViewModel() {

    val jokeLiveData: LiveData<StateResponse<JokeVO>> get() = _jokeLiveData
    private val _jokeLiveData = MutableLiveData<StateResponse<JokeVO>>()

    fun fetchJoke(category: String) {
        _jokeLiveData.value = StateLoading()
        fetchJokesUseCase.execute(FetchJokesUseCase.Param((category))).subscribe(
            { joke ->
                _jokeLiveData.value = StateSuccess(joke)
            },
            { e ->
                _jokeLiveData.value = StateError(e)
            }
        ).let { disposables.add(it) }
    }
}