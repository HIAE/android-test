package br.com.antoniocgrande.chucknoia.presentation.activities

import br.com.antoniocgrande.chucknoia.data.model.Category
import br.com.antoniocgrande.chucknoia.data.model.Joke

/* Copyright 2020.
 ************************************************************
 * Project     : ChuckNoia
 * Description : br.com.antoniocgrande.chucknoia.presentation.activities
 * Created by  : antoniocgrande
 * Date        : 04/05/2020 23:22
 ************************************************************/
sealed class HomeState {

    object ShowLoading : HomeState()

    object HideLoading : HomeState()

    object HideEmptyState : HomeState()

    data class ListCategoriesSuccess(val listCategoriesResult: List<Category>) : HomeState()

    data class ListCategoriesFail(val message: String?) : HomeState()

    data class RandomJokeSuccess(val joke: Joke) : HomeState()

    data class RandomJokeFail(val message: String?) : HomeState()

}