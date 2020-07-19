package com.renanparis.chuckjokes.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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

    private fun initConnectionListener() {
        InternetAvailabilityChecker.init(this);
        val mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance();
        mInternetAvailabilityChecker.addInternetConnectivityListener(this);
    }

    private fun searchCategories() {
        viewModel.getCategories().observe(this, Observer { resources ->
            when (resources.status) {

                Status.SUCCESS -> {
                    rv_list_category.visibility = View.VISIBLE
                    resources.data?.let { categories ->
                        adapter.update(categories)
                    }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    rv_list_category.visibility = View.GONE
                    val dialog = ItemNotFoundDialog(this)
                    dialog.onItemClickListener = {dialog ->
                        dialog.dismiss()
                    }
                    dialog.show()
                }
            }
        })
    }

    private fun configRecyclerView() {
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
