package br.com.antoniocgrande.chucknoia.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.antoniocgrande.chucknoia.R
import br.com.antoniocgrande.chucknoia.data.model.Category
import br.com.antoniocgrande.chucknoia.presentation.activities.HomeActivity.Companion.viewModel
import br.com.antoniocgrande.chucknoia.presentation.activities.HomeState
import br.com.antoniocgrande.chucknoia.presentation.adapters.CategoriesAdapter
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : Fragment() {


    /**
     *
     * OVERRIDE METHODS
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_categories, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupState()
        setupCategories()
        setupRecyclerView()
    }


    /**
     *
     * SETUP METHODS
     *
     */
    private fun setupState() {
        viewModel.getStateCategories().observe(this, Observer { state ->
            when (state) {
                is HomeState.ListCategories -> listCategories(state.listCategoriesResult)
                is HomeState.Fail -> fail(state)
            }
        })
    }

    private fun setupCategories() {
        viewModel.listCategories()
    }

    private fun setupRecyclerView() = with(recyclerViewCategories) {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(requireContext())
    }


    /**
     *
     * STATE METHODS
     *
     */
    private fun listCategories(listCategoriesResult: MutableList<Category>) {
        recyclerViewCategories.adapter = CategoriesAdapter(listCategoriesResult) {
            Toast.makeText(activity, "clicado $it", Toast.LENGTH_LONG).show()
        }
    }

    private fun fail(state: HomeState.Fail) {

    }

}
