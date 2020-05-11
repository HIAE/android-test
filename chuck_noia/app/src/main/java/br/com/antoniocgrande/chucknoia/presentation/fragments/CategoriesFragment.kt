package br.com.antoniocgrande.chucknoia.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.antoniocgrande.chucknoia.R
import br.com.antoniocgrande.chucknoia.data.model.Category
import br.com.antoniocgrande.chucknoia.presentation.activities.HomeState
import br.com.antoniocgrande.chucknoia.presentation.adapters.CategoriesAdapter
import br.com.antoniocgrande.chucknoia.presentation.viewmodel.ViewModel
import kotlinx.android.synthetic.main.empty_state.*
import kotlinx.android.synthetic.main.fragment_categories.*
import org.koin.android.ext.android.inject

class CategoriesFragment : Fragment() {


    /**
     *
     * ATRIBUTES
     *
     */
    private val viewModel: ViewModel by inject()


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
        setupListeners()
        setupToolbar()
    }


    /**
     *
     * SETUP METHODS
     *
     */
    private fun setupState() {
        viewModel.getState().observe(this, Observer { state ->
            when (state) {
                is HomeState.ListCategoriesSuccess -> listCategoriesSuccess(state.listCategoriesResult)
                is HomeState.ListCategoriesFail -> listCategoriesFail()
                is HomeState.HideEmptyState -> hideEmptyState()
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

    private fun setupListeners() {
        textViewTryAgain.setOnClickListener { viewModel.listCategories() }
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity?)?.supportActionBar?.subtitle = ""
    }


    /**
     *
     * STATE METHODS
     *
     */
    private fun listCategoriesSuccess(listCategoriesResult: List<Category>) {
        recyclerViewCategories.adapter = CategoriesAdapter(listCategoriesResult) {
            Navigation.findNavController(requireActivity(), R.id.fragmentNavHost)
                .navigate(R.id.action_categoriesFragment_to_jokeFragment, Bundle().apply {
                    putString("CATEGORY", it)
                })
        }
    }

    private fun listCategoriesFail() {
        showEmptyState()
    }

    private fun showEmptyState() {
        constraintLayoutEmptyState.visibility = View.VISIBLE
    }

    private fun hideEmptyState() {
        constraintLayoutEmptyState.visibility = View.GONE
    }

}
