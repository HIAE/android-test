package com.lcardoso.android_test.ui.categories

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
        is StateSuccess<CategoriesVO> -> setupRecyclerView(state.data.categories)
        is StateError -> Toast.makeText(this, state.e.message, Toast.LENGTH_SHORT).show()
        is StateLoading -> Toast.makeText(this, "Carregando...", Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView(data: List<String>) {
        with(rvCategories) {
            layoutManager =
                LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = CategoriesAdapter(data) { category ->
//                startActivity<MoviesActivity>("GENRE_ID" to genre.id, "GENRE_NAME" to genre.name)
                Toast.makeText(this.context, category, Toast.LENGTH_LONG).show()
            }
        }
    }
}