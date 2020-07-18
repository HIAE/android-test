package com.renanparis.chuckjokes.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.renanparis.chuckjokes.usecase.CategoryUseCase

class CategoryViewModel(private val useCase: CategoryUseCase): ViewModel() {

    fun getCategories() = useCase.getCategories()
}