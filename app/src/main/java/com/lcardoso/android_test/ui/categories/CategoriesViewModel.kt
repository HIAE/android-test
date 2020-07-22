package com.lcardoso.android_test.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lcardoso.android_test.BaseViewModel
import com.lcardoso.android_test.data.StateError
import com.lcardoso.android_test.data.StateLoading
import com.lcardoso.android_test.data.StateResponse
import com.lcardoso.android_test.data.StateSuccess
import com.lcardoso.android_test.data.model.CategoriesVO
import com.lcardoso.android_test.usecase.FetchCategoriesUseCase

class CategoriesViewModel(
    private val fetchCategoriesUseCase: FetchCategoriesUseCase
) : BaseViewModel() {

    val categoriesLiveData: LiveData<StateResponse<CategoriesVO>> get() = _categoriesLiveData
    private val _categoriesLiveData = MutableLiveData<StateResponse<CategoriesVO>>()

    fun fetchCategories() {
        _categoriesLiveData.value = StateLoading()
        fetchCategoriesUseCase.execute().subscribe(
            { categories ->
                _categoriesLiveData.value = StateSuccess(categories)
            },
            { e ->
                _categoriesLiveData.value = StateError(e)
            }
        ).let { disposables.add(it) }
    }
}