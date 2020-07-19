package com.renanparis.chuckjokes.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.renanparis.chuckjokes.R
import com.renanparis.chuckjokes.data.model.Joke
import com.renanparis.chuckjokes.ui.viewmodel.RandomJokeViewModel
import com.renanparis.chuckjokes.utils.Status
import kotlinx.android.synthetic.main.activity_random_joke.*
import org.koin.android.viewmodel.ext.android.viewModel

class RandomJokeActivity : AppCompatActivity() {

    private lateinit var textJoke: TextView
    private val viewModel: RandomJokeViewModel by viewModel()
    private lateinit var joke: Joke
    private val category: String by lazy {
        intent.getStringExtra("category")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_joke)
        initViews()
        searchRandomJoke()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite_joke, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.favorite_joke_white -> {
                    joke.favorite = true
                    saveFavoriteJoke()
                    invalidateOptionsMenu()
                }

            R.id.favorite_joke_board -> {
                joke.favorite = false
                deleteFavoriteJoke()
                invalidateOptionsMenu()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteFavoriteJoke() {
        viewModel.deleteJoke(joke).observe(this, Observer {
            if (it.status == Status.ERROR) {
                TODO ("mensaem erro ou mensagem de sucesso")
            }
        })

    }

    private fun saveFavoriteJoke() {
        viewModel.saveJoke(joke).observe(this, Observer {
            if (it.status == Status.ERROR) {
                TODO ("mensaem erro ou mensagem de sucesso")
            }
        })

    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        if (::joke.isInitialized && joke.favorite) {
            menu?.findItem(R.id.favorite_joke_board)?.isVisible = true
            menu?.findItem(R.id.favorite_joke_white)?.isVisible = false
        } else {
            menu?.findItem(R.id.favorite_joke_board)?.isVisible = false
            menu?.findItem(R.id.favorite_joke_white)?.isVisible = true
        }

        return super.onPrepareOptionsMenu(menu)
    }

    private fun searchRandomJoke() {
        viewModel.getRandomJoke(category).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {

                    Status.LOADING -> {

                    }
                    Status.ERROR -> {
                        Toast.makeText(this, resource.message, Toast.LENGTH_LONG).show()
                    }
                    Status.SUCCESS -> {
                        resource.data?.let {
                            joke = it
                        }
                        textJoke.text = joke?.value
                    }
                }
            }
        })
    }

    private fun initViews() {
        textJoke = tv_random_joke
    }
}
