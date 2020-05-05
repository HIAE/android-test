package br.com.antoniocgrande.chucknoia.presentation.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.antoniocgrande.chucknoia.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {


    /**
     *
     * ATRIBUTES
     *
     */
    private val homeViewModel by lazy { HomeViewModel() }


    /**
     *
     * OVERRIDE METHODS
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        setupState()
    }


    /**
     *
     * SETUP METHODS
     *
     */
    private fun setupView() {
        setContentView(R.layout.activity_home)
    }

    private fun setupState() {
        homeViewModel.getStateCategories().observe(this, Observer { state ->
            when (state) {
                is HomeState.ShowLoading -> showLoading()
                is HomeState.HideLoading -> hideLoading()
                is HomeState.ListCategories -> listCategories(state.listCategoriesResult)
            }
        })

        homeViewModel.listCategories()
    }


    /**
     *
     * STATE METHODS
     *
     */
    private fun showLoading() {
        linearLayoutProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        linearLayoutProgressBar.visibility = View.GONE
    }

    private fun listCategories(listCategoriesResult: List<String>) {
        Toast.makeText(this, "$listCategoriesResult ", Toast.LENGTH_LONG).show()
    }

}
