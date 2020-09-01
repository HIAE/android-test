package com.lcardoso.android_test.usecase

import com.lcardoso.android_test.data.JokesRepositoryImp

class FetchCategoriesUseCase(
    private val repository: JokesRepositoryImp
) {
    fun execute() = repository.fetchCategories()
}