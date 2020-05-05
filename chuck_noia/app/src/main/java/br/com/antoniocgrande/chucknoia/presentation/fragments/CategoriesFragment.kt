package br.com.antoniocgrande.chucknoia.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.antoniocgrande.chucknoia.R
import br.com.antoniocgrande.chucknoia.presentation.activities.HomeActivity.Companion.homeViewModel

class CategoriesFragment : Fragment() {


    /**
     *
     * OVERRIDE METHODS
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_categories, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupCategories()
    }


    /**
     *
     * SETUP METHODS
     *
     */
    private fun setupCategories() {
        homeViewModel.listCategories()
    }

}
