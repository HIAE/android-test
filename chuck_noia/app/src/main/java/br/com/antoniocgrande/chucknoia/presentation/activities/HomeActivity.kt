package br.com.antoniocgrande.chucknoia.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import br.com.antoniocgrande.chucknoia.R
import br.com.antoniocgrande.chucknoia.presentation.viewmodel.ViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {


    /**
     *
     * ATRIBUTES
     *
     */
    private val navController by lazy { Navigation.findNavController(this, R.id.fragmentNavHost) }


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

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }


    /**
     *
     * SETUP METHODS
     *
     */
    private fun setupView() {
        setContentView(R.layout.activity_home)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    private fun setupState() {
        viewModel.getStateCategories().observe(this, Observer { state ->
            when (state) {
                is HomeState.ShowLoading -> showLoading()
                is HomeState.HideLoading -> hideLoading()
            }
        })
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


    companion object {
        val viewModel by lazy { ViewModel() }
    }

}
