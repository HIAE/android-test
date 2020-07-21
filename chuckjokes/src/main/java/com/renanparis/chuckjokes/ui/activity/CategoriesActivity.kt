package com.renanparis.chuckjokes.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.renanparis.chuckjokes.R
import com.renanparis.chuckjokes.ui.adapter.CategoriesAdapter
import com.renanparis.chuckjokes.ui.dialog.ItemNotFoundDialog
import com.renanparis.chuckjokes.ui.viewmodel.CategoryViewModel
import com.renanparis.chuckjokes.utils.Status
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker
import com.treebo.internetavailabilitychecker.InternetConnectivityListener
import kotlinx.android.synthetic.main.activity_categories.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class CategoriesActivity : AppCompatActivity(), InternetConnectivityListener {

    private val adapter: CategoriesAdapter by inject()
    private val viewModel: CategoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        configRecyclerView()
        searchCategories()
        initConnectionListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_categories, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.list_favorites_jokes ->
                goToFavoritesList()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToFavoritesList() {
        startActivity(Intent(this, FavoritesJokesActivity::class.java))

    }

    private fun initConnectionListener() {
        InternetAvailabilityChecker.init(this);
        val mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance();
        mInternetAvailabilityChecker.addInternetConnectivityListener(this);
    }

    private fun searchCategories() {
        viewModel.getCategories().observe(this, Observer { resources ->
            when (resources.status) {

                Status.SUCCESS -> {
                    categories_joke_progress.visibility = View.GONE
                    resources.data?.let { categories ->
                        adapter.update(categories)
                    }
                }
                Status.LOADING -> {
                    categories_joke_progress.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    categories_joke_progress.visibility = View.GONE
                    ItemNotFoundDialog(this).show()
                }
            }
        })
    }

    private fun configRecyclerView() {
        rv_list_category.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rv_list_category.adapter = adapter
        configAdapter()
    }

    private fun configAdapter() {
        adapter.onItemClickListener = { category ->
            showJoke(category)
        }
    }

    private fun showJoke(category: String) {
        val intent = Intent(this, RandomJokeActivity::class.java)
        intent.putExtra(KEY_CATEGORY_JOKE, category)
        startActivity(intent)
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
        if (isConnected) {
            searchCategories()
        }
    }

    companion object {
        const val KEY_CATEGORY_JOKE = "category"
    }
}
