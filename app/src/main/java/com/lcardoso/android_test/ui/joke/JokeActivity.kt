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
    private var currentJokeId: String? = null

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        setupObservers()
        viewModel.fetchJoke(category)
    }

    private fun initViews() {
        tvCategory.text = category
        ivBack.setOnClickListener { finish() }
        btnNextJoke.setOnClickListener { nextJoke() }
    }

    @ExperimentalStdlibApi
    private fun setupObservers() {
        viewModel.jokeLiveData.nomNullObserve(this) { state ->
            processRequest(state)
        }

        viewModel.hasPreviousJokeLiveData.nomNullObserve(this) { hasPreviousJoke ->
            btnPreviousJoke.run {
                changeVisibility(hasPreviousJoke)
                if (hasPreviousJoke) {
                    val previousJoke = viewModel.recentJokes.last()
                    setOnClickListener { viewModel.fetchPreviousJoke(previousJoke) }
                }
            }
        }

        viewModel.previousJokeLiveData.nomNullObserve(this) { state ->
            processRequest(state)
        }
    }

    private fun processRequest(state: StateResponse<JokeVO>) = when (state) {
        is StateSuccess<JokeVO> -> setupJoke(state.data)
        is StateError -> showGenericError()
        is StateLoading -> loadingJoke()
    }

    private fun setupJoke(joke: JokeVO) {
        currentJokeId = joke.id
        pbJoke.changeVisibility(false)
        errorJokeView.changeVisibility(false)
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

    private fun nextJoke() {
        currentJokeId?.let { viewModel.setRecentJokes(it) }
        viewModel.fetchJoke(category)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    companion object {
        const val CATEGORY = "jokeCategory"
    }
}