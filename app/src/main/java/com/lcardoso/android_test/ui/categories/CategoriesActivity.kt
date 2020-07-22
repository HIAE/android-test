package com.lcardoso.android_test.ui.categories

import android.os.Bundle
import android.widget.Toast
import com.lcardoso.android_test.BaseActivity
import com.lcardoso.android_test.R
import com.lcardoso.android_test.data.StateError
import com.lcardoso.android_test.data.StateLoading
import com.lcardoso.android_test.data.StateResponse
import com.lcardoso.android_test.data.StateSuccess
import com.lcardoso.android_test.data.model.CategoriesVO
import com.lcardoso.android_test.util.nomNullObserve
import kotlinx.android.synthetic.main.activity_categories.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesActivity : BaseActivity(
    layoutId = R.layout.activity_categories
) {

    private val viewModel: CategoriesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
        viewModel.fetchCategories()
    }

    private fun setupObservers() {
        viewModel.categoriesLiveData.nomNullObserve(this) { state ->
            processRequest(state)
        }
    }

    private fun processRequest(state: StateResponse<CategoriesVO>) = when (state) {
        is StateSuccess<CategoriesVO> -> textView.text = state.data.categories.size.toString()
        is StateError -> Toast.makeText(this, state.e.message, Toast.LENGTH_SHORT).show()
        is StateLoading -> Toast.makeText(this, "Carregando...", Toast.LENGTH_SHORT).show()
    }
}