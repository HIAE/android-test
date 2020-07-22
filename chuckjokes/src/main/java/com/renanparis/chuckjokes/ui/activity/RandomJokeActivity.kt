package com.renanparis.chuckjokes.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.renanparis.chuckjokes.R
import com.renanparis.chuckjokes.data.model.Joke
import com.renanparis.chuckjokes.ui.activity.CategoriesActivity.Companion.KEY_CATEGORY_JOKE
import com.renanparis.chuckjokes.ui.activity.extensions.validateRemovedFavorite
import com.renanparis.chuckjokes.ui.activity.extensions.validateSavedFavorite
import com.renanparis.chuckjokes.ui.dialog.ItemNotFoundDialog
import com.renanparis.chuckjokes.ui.viewmodel.RandomJokeViewModel
import com.renanparis.chuckjokes.utils.Status
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_random_joke.*
import org.koin.android.viewmodel.ext.android.viewModel

class RandomJokeActivity : AppCompatActivity() {

    private lateinit var textJoke: TextView
    private lateinit var imageJoke: ImageView
    private lateinit var btnJoke: Button

    private val viewModel: RandomJokeViewModel by viewModel()
    private lateinit var joke: Joke
    private val category: String by lazy {
        intent.getStringExtra(KEY_CATEGORY_JOKE)
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
            R.id.list_favorites_jokes -> {
                goToFavoritesJoke()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToFavoritesJoke() {
        startActivity(Intent(this, FavoritesJokesActivity::class.java))
    }

    private fun deleteFavoriteJoke() {
        viewModel.deleteJoke(joke).observe(this, Observer { resources ->
            validateRemovedFavorite(resources.status)
        })
    }

    private fun saveFavoriteJoke() {
        viewModel.saveJoke(joke).observe(this, Observer { resources ->
            validateSavedFavorite(resources.status)
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (::joke.isInitialized && joke.favorite) {
            changeColorToItemSaved(menu)
        } else {
            changeColorToItemNotSaved(menu)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    private fun changeColorToItemNotSaved(menu: Menu?) {
        menu?.findItem(R.id.favorite_joke_board)?.isVisible = false
        menu?.findItem(R.id.favorite_joke_white)?.isVisible = true
    }

    private fun changeColorToItemSaved(menu: Menu?) {
        menu?.findItem(R.id.favorite_joke_board)?.isVisible = true
        menu?.findItem(R.id.favorite_joke_white)?.isVisible = false
    }

    private fun searchRandomJoke() {
        viewModel.getRandomJoke(category).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {

                    Status.LOADING -> {
                        random_joke_progress.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        random_joke_progress.visibility = View.GONE
                        showDialogError()
                    }
                    Status.SUCCESS -> {
                        random_joke_progress.visibility = View.GONE
                        resource.data?.let { jokeReceived ->
                            joke = jokeReceived
                        }
                        setViews()

                    }
                }
            }
        })
    }

    private fun showDialogError() {
        val dialog = ItemNotFoundDialog(this,
                this.getString(R.string.message_warning_dialog))
        dialog.onItemClickListener = {
            finish()
        }
        dialog.show()
    }

    private fun setViews() {
        Picasso.get().load(joke.icon_url).into(imageJoke)
        textJoke.text = joke.value
        invalidateOptionsMenu()
    }

    private fun initViews() {
        textJoke = tv_random_joke
        imageJoke = image_random_joke
        btnJoke = btn_random_joke
        configListener()
    }

    private fun configListener() {
        btnJoke.setOnClickListener {
            searchRandomJoke()
        }
    }
}
