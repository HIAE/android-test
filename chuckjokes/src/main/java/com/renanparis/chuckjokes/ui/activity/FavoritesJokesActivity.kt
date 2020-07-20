package com.renanparis.chuckjokes.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.renanparis.chuckjokes.R
import com.renanparis.chuckjokes.data.model.Joke
import com.renanparis.chuckjokes.ui.activity.extensions.showMessage
import com.renanparis.chuckjokes.ui.adapter.FavoritesJokesAdapter
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
        viewModel.getFavoritesJokes().observe(this, Observer {resources ->

            when (resources.status) {

                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    resources.data?.let {jokes->
                    adapter.update(jokes)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, resources.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun configRecyclerView() {
        rv_favorites_jokes_list.adapter = adapter
        configAdapter()
    }

    private fun configAdapter() {
        val dialog = RemoveFavoriteJokeDialog(this)
        adapter.onItemClickListener = {joke->
            dialog.show()
            dialog.onItemClickListener = {
                removeFavoriteJoke(joke)
                adapter.removeFavoriteJoke(joke)
            }
        }
    }

    private fun removeFavoriteJoke(joke: Joke) {
        viewModel.removeFavoriteJoke(joke).observe(this, Observer {resources ->
            if (resources.status == Status.SUCCESS) {
                showMessage(getString(R.string.message_remove_favorite))
            } else if (resources.status == Status.ERROR) {
                showMessage(resources.message.toString())
            }
        })
    }
}
