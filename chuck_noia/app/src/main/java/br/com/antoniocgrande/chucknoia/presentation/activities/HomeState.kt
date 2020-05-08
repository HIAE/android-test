package br.com.antoniocgrande.chucknoia.presentation.activities

import br.com.antoniocgrande.chucknoia.data.model.Category

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

    data class ListCategories(val listCategoriesResult: MutableList<Category>) : HomeState()

    data class Fail(val message: String?) : HomeState()

}