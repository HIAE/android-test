package com.lcardoso.android_test.ui.categories

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.lcardoso.android_test.BaseActivity
import com.lcardoso.android_test.R
import com.lcardoso.android_test.data.StateError
import com.lcardoso.android_test.data.StateLoading
import com.lcardoso.android_test.data.StateResponse
import com.lcardoso.android_test.data.StateSuccess
import com.lcardoso.android_test.data.model.CategoriesVO
import com.lcardoso.android_test.ui.favorites.FavoritesActivity
import com.lcardoso.android_test.ui.joke.JokeActivity
import com.lcardoso.android_test.util.changeVisibility
import com.lcardoso.android_test.util.nomNullObserve
import kotlinx.android.synthetic.main.activity_categories.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesActivity : BaseActivity(
    layoutId = R.layout.activity_categories
) {

    private val viewModel: CategoriesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupListener()
        setupObservers()
        viewModel.fetchCategories()
    }

    private fun setupObservers() {
        viewModel.categoriesLiveData.nomNullObserve(this) { state ->
            processRequest(state)
        }
    }

    private fun setupListener() {
        btnFavorites.setOnClickListener { startActivity<FavoritesActivity>() }
    }

    private fun processRequest(state: StateResponse<CategoriesVO>) = when (state) {
        is StateSuccess<CategoriesVO> -> setupRecyclerView(state.data.categories)
        is StateError -> showGenericError()
        is StateLoading -> loadingCategories()
    }

    private fun setupRecyclerView(data: List<String>) {
        pbCategories.changeVisibility(false)
        with(rvCategories) {
            layoutManager =
                LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = CategoriesAdapter(data) { category ->
                startActivity<JokeActivity>(JokeActivity.CATEGORY to category)
            }
            this.changeVisibility(true)
        }
    }

    private fun showGenericError() {
        pbCategories.changeVisibility(false)
        errorView.changeVisibility(true)
        ivTryAgain.setOnClickListener { viewModel.fetchCategories() }
    }

    private fun loadingCategories() {
        pbCategories.changeVisibility(true)
        errorView.changeVisibility(false)
        rvCategories.changeVisibility(false)
    }
}