package com.lcardoso.android_test.ui.favorites

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lcardoso.android_test.BaseActivity
import com.lcardoso.android_test.R
import com.lcardoso.android_test.data.database.JokeEntity
import com.lcardoso.android_test.util.changeVisibility
import com.lcardoso.android_test.util.nomNullObserve
import kotlinx.android.synthetic.main.activity_favorites.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesActivity : BaseActivity(
    layoutId = R.layout.activity_favorites
) {

    private val viewModel: FavoritesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        setupObservers()
        viewModel.fetchFavoriteJokes()
    }

    private fun initViews() {
        setupToolbar()
    }

    private fun setupObservers() {
        viewModel.favoriteJokesLiveData.nomNullObserve(this) { jokes ->
            processRequest(jokes)
        }
    }

    private fun processRequest(jokes: List<JokeEntity>) {
        if (jokes.isEmpty()) {
            setupVisibility(hasJokes = false)
        } else {
            setupVisibility(hasJokes = true)
            setupRecyclerView(jokes)
        }
    }

    private fun setupToolbar() {
        tvHeading.text = getString(R.string.favorites)
        ivBack.setOnClickListener { finish() }
    }

    private fun setupRecyclerView(jokes: List<JokeEntity>) {
        with(rvFavorites) {
            layoutManager =
                LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = FavoritesAdapter(jokes) { joke ->
                viewModel.removeFavoriteJoke(joke)
                viewModel.fetchFavoriteJokes()
            }
        }
    }

    private fun setupVisibility(hasJokes: Boolean) {
        rvFavorites.changeVisibility(hasJokes)
        ivNoFavoriteJokes.changeVisibility(!hasJokes)
        tvNoFavoriteJokes.changeVisibility(!hasJokes)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}