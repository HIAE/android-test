package br.com.antoniocgrande.chucknoia.presentation.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/* Copyright 2020.
 ************************************************************
 * Project     : ChuckNoia
 * Description : br.com.antoniocgrande.chucknoia.presenter.activities
 * Created by  : antoniocgrande
 * Date        : 04/05/2020 18:16
 ************************************************************/
class HomeViewModel : ViewModel() {

    private val _stateCategories by lazy { MutableLiveData<HomeState>() }

    fun getStateCategories() = _stateCategories

    fun listCategories() {
        _stateCategories.value = HomeState.ShowLoading
        _stateCategories.postValue(
            HomeState.ListCategories(
                listOf(
                    "animal",
                    "career",
                    "celebrity",
                    "dev",
                    "explicit",
                    "fashion",
                    "food",
                    "history",
                    "money",
                    "movie",
                    "music",
                    "political",
                    "religion",
                    "science",
                    "sport",
                    "travel"
                )
            )
        )
        _stateCategories.value = HomeState.HideLoading
    }

}