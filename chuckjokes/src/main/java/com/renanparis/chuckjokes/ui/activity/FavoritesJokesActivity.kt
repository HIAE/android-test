package com.renanparis.chuckjokes.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.renanparis.chuckjokes.R
import com.renanparis.chuckjokes.data.model.Joke
import com.renanparis.chuckjokes.ui.activity.extensions.validateRemovedFavorite
import com.renanparis.chuckjokes.ui.adapter.FavoritesJokesAdapter
import com.renanparis.chuckjokes.ui.dialog.ItemNotFoundDialog
import com.renanparis.chuckjokes.ui.dialog.RemoveFavoriteJokeDialog
import com.renanparis.chuckjokes.ui.viewmodel.FavoritesJokesViewModel
import com.renanparis.chuckjokes.utils.Status
import kotlinx.android.synthetic.main.activity_favorites_jokes.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class FavoritesJokesActivity : AppCompatActivity() {

    private val adapter: FavoritesJokesAdapter by inject()
    private val viewModel: FavoritesJokesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites_jokes)
        configRecyclerView()
        getFavoritesJokes()
    }

    private fun getFavoritesJokes() {
        viewModel.getFavoritesJokes().observe(this, Observer { resources ->

            when (resources.status) {

                Status.LOADING -> {
                    list_favorites_joke_progress.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    list_favorites_joke_progress.visibility = View.GONE
                    resources.data?.let { jokes ->
                        if (jokes.isEmpty()) {
                          showDialog(this.getString(R.string.message_warning_dialog_no_favorites))
                        }
                        adapter.update(jokes)
                    }
                }
                Status.ERROR -> {
                    list_favorites_joke_progress.visibility = View.GONE
                    showDialog(this.getString(R.string.message_warning_dialog_favorites_error))
                }
            }
        })
    }

    private fun showDialog(message: String) {
        val itemNotFoundDialog = ItemNotFoundDialog(this, message)
        itemNotFoundDialog.show()
        itemNotFoundDialog.onItemClickListener = {
            finish()
        }
    }

    private fun configRecyclerView() {
        rv_favorites_jokes_list.adapter = adapter
        configAdapter()
    }

    private fun configAdapter() {
        val dialog = RemoveFavoriteJokeDialog(this)
        adapter.onItemClickListener = { joke ->
            showDialogRemoveFavariteJoke(dialog, joke)
        }
    }

    private fun showDialogRemoveFavariteJoke(dialog: RemoveFavoriteJokeDialog, joke: Joke) {
        dialog.show()
        dialog.onItemClickListener = {
            removeFavoriteJoke(joke)
            adapter.removeFavoriteJoke(joke)
        }
    }

    private fun removeFavoriteJoke(joke: Joke) {
        viewModel.removeFavoriteJoke(joke).observe(this, Observer { resources ->
            validateRemovedFavorite(resources.status)
        })
    }
}
