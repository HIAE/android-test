package com.lcardoso.android_test.ui.joke

import android.os.Bundle
import com.lcardoso.android_test.BaseActivity
import com.lcardoso.android_test.R
import com.lcardoso.android_test.data.StateError
import com.lcardoso.android_test.data.StateLoading
import com.lcardoso.android_test.data.StateResponse
import com.lcardoso.android_test.data.StateSuccess
import com.lcardoso.android_test.data.model.JokeVO
import com.lcardoso.android_test.util.changeVisibility
import com.lcardoso.android_test.util.nomNullObserve
import kotlinx.android.synthetic.main.activity_joke.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class JokeActivity : BaseActivity(
    layoutId = R.layout.activity_joke
) {

    private val viewModel: JokeViewModel by viewModel()
    private val category: String by lazy { intent.getStringExtra(CATEGORY) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        setupObservers()
        viewModel.fetchJoke(category)
    }

    private fun initViews() {
        tvCategory.text = category
        ivBack.setOnClickListener { onBackPressed() }
        btnNextJoke.setOnClickListener { viewModel.fetchJoke(category) }
    }

    private fun setupObservers() {
        viewModel.jokeLiveData.nomNullObserve(this) { state ->
            processRequest(state)
        }
    }

    private fun processRequest(state: StateResponse<JokeVO>) = when (state) {
        is StateSuccess<JokeVO> -> setupJoke(state.data)
        is StateError -> showGenericError()
        is StateLoading -> loadingJoke()
    }

    private fun setupJoke(joke: JokeVO) {
        pbJoke.changeVisibility(false)
        tvJoke.run {
            changeVisibility(true)
            text = joke.joke
        }
    }

    private fun showGenericError() {
        tvJoke.changeVisibility(false)
        pbJoke.changeVisibility(false)
        errorJokeView.changeVisibility(true)
        ivJokeTryAgain.setOnClickListener { viewModel.fetchJoke(category) }
    }

    private fun loadingJoke() {
        pbJoke.changeVisibility(true)
        errorJokeView.changeVisibility(false)
        tvJoke.changeVisibility(false)
    }

    companion object {
        const val CATEGORY = "jokeCategory"
    }
}