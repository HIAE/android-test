package com.araujoraul.aechuck.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.aechuck.MainApplication
import com.araujoraul.aechuck.R
import com.araujoraul.aechuck.fragments.BaseFragment
import com.araujoraul.aechuck.fragments.categories.adapter.CategoriesAdapter
import com.araujoraul.aechuck.fragments.joke.JokeDialogFragment
import com.araujoraul.aechuck.utils.hide
import com.araujoraul.aechuck.utils.show
import com.araujoraul.aechuck.utils.toast
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : BaseFragment() {

    private lateinit var viewModel: CategoriesViewModel
    private lateinit var recyclerView: RecyclerView
    private var categoryList = MainApplication.categoryList
    private val piadaDialogFragment = JokeDialogFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_categories, container, false)

        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        recyclerView = root.findViewById(R.id.recyclerView_categorias)

        recyclerView.let {
            it.layoutManager = LinearLayoutManager(requireActivity())
            it.itemAnimator = DefaultItemAnimator()
            it.setHasFixedSize(true)
        }

        if (savedInstanceState != null){
            val getList = savedInstanceState.getStringArrayList("list")
            if (getList != null) categoryList.addAll(getList)
        }

        setupMessagesErrorAndProgressBar()
        return root
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenCreated {
            taskCategories()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("list", categoryList)
    }

    private fun taskCategories() {

        with(viewModel) {

            taskCategories()

            showCategories.observe(viewLifecycleOwner, Observer { response ->

                if (response.isNotEmpty())
                    if (categoryList.isEmpty()) {

                        categoryList.addAll(response)

                        recyclerView.adapter = CategoriesAdapter(categoryList) { categoria: String ->

                            val args = Bundle()
                            args.putString("category", categoria)
                            piadaDialogFragment.arguments = args
                            piadaDialogFragment.show(requireActivity().supportFragmentManager, "tag")

                        }
                    } else {

                        recyclerView.adapter = CategoriesAdapter(categoryList) { categoria: String ->

                            val args = Bundle()
                            args.putString("category", categoria)
                            piadaDialogFragment.arguments = args
                            piadaDialogFragment.show(requireActivity().supportFragmentManager, "tag")

                        }

                    }
            })
        }
    }

    private fun setupMessagesErrorAndProgressBar(){

        with(viewModel){

            showProgressBar.observe(viewLifecycleOwner, Observer {
                if (it == true) progress_categories.show()
                else progress_categories.hide()
            })

            showMessageNoInternet.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled().let {
                    if (it == true) context?.toast("Sem conexão com a internet :(\nTente novamente daqui a pouco...")
                }
            })

            showMessageServerError.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled().let {
                    if (it == true) context?.toast("Erro no servidor interno :(\nTente novamente daqui a pouco...")
                }
            })

        }

    }

}