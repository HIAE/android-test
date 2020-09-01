package com.lcardoso.android_test.ui.joke

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lcardoso.android_test.BaseViewModel
import com.lcardoso.android_test.data.StateError
import com.lcardoso.android_test.data.StateLoading
import com.lcardoso.android_test.data.StateResponse
import com.lcardoso.android_test.data.StateSuccess
import com.lcardoso.android_test.data.database.JokeEntity
import com.lcardoso.android_test.data.model.JokeVO
import com.lcardoso.android_test.usecase.*

class JokeViewModel(
    private val fetchJokesUseCase: FetchJokesUseCase,
    private val fetchPreviousJokeUseCase: FetchPreviousJokeUseCase,
    private val addFavoriteJokeUseCase: AddFavoriteJokeUseCase,
    private val removeFavoriteJokeUseCase: RemoveFavoriteJokeUseCase,
    private val isFavoriteJokeUseCase: IsFavoriteJokeUseCase
) : BaseViewModel() {

    val jokeLiveData: LiveData<StateResponse<JokeVO>> get() = _jokeLiveData
    private val _jokeLiveData = MutableLiveData<StateResponse<JokeVO>>()

    val previousJokeLiveData: LiveData<StateResponse<JokeVO>> get() = _previousJokeLiveData
    private val _previousJokeLiveData = MutableLiveData<StateResponse<JokeVO>>()

    val hasPreviousJokeLiveData: LiveData<Boolean> get() = _hasPreviousJokeLiveData
    private val _hasPreviousJokeLiveData = MutableLiveData<Boolean>()

    val isFavoriteJokeLiveData: LiveData<Boolean> get() = _isFavoriteJokeLiveData
    private val _isFavoriteJokeLiveData = MutableLiveData<Boolean>()

    var recentJokes: MutableList<String> = mutableListOf()

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

    @ExperimentalStdlibApi
    fun fetchPreviousJoke(jokeId: String) {
        recentJokes.removeLast()
        _previousJokeLiveData.value = StateLoading()
        fetchPreviousJokeUseCase.execute(FetchPreviousJokeUseCase.Param((jokeId))).subscribe(
            { joke ->
                _previousJokeLiveData.value = StateSuccess(joke)
            },
            { e ->
                _previousJokeLiveData.value = StateError(e)
            }
        ).let { disposables.add(it) }
        _hasPreviousJokeLiveData.value = recentJokes.isNotEmpty()
    }

    fun setRecentJokes(jokeId: String) {
        recentJokes.add(jokeId)
        _hasPreviousJokeLiveData.value = recentJokes.isNotEmpty()
    }

    fun isFavoriteJoke(id: String) {
        isFavoriteJokeUseCase.execute(IsFavoriteJokeUseCase.Param(id = id)).subscribe(
            {
                _isFavoriteJokeLiveData.value = true
            },
            {
                _isFavoriteJokeLiveData.value = false
            }
        ).let { disposables.add(it) }
    }

    fun addFavoriteJoke(joke: JokeEntity) {
        addFavoriteJokeUseCase.execute(
            AddFavoriteJokeUseCase.Param(
                id = joke.id,
                category = joke.category,
                joke = joke.joke

            )
        ).subscribe(
            {}, { e ->
                Log.d("lero", e.message)
            }
        ).let { disposables.add(it) }
    }

    fun removeFavoriteJoke(joke: JokeEntity) {
        removeFavoriteJokeUseCase.execute(
            RemoveFavoriteJokeUseCase.Param(
                id = joke.id,
                category = joke.category,
                joke = joke.joke

            )
        ).subscribe(
            {}, { e ->
                Log.d("lero", e.message)
            }
        ).let { disposables.add(it) }
    }
}