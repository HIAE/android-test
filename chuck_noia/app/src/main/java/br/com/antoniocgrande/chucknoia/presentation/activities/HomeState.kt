package br.com.antoniocgrande.chucknoia.presentation.activities

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

    data class ListCategories(val listCategoriesResult: List<String>) : HomeState()

}