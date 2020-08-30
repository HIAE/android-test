package com.araujoraul.aechuck.fragments.categorias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.aechuck.MainApplication
import com.araujoraul.aechuck.R
import com.araujoraul.aechuck.fragments.BaseFragment
import com.araujoraul.aechuck.fragments.categorias.adapter.CategoriasAdapter
import com.araujoraul.aechuck.utils.hide
import com.araujoraul.aechuck.utils.show
import com.araujoraul.aechuck.utils.toast
import kotlinx.android.synthetic.main.fragment_categorias.*

class CategoriasFragment : BaseFragment() {

    private lateinit var viewModel: CategoriasViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_categorias, container, false)

        viewModel = ViewModelProvider(this).get(CategoriasViewModel::class.java)

        recyclerView = root.findViewById(R.id.recyclerView_categorias)

        recyclerView.let {
            it.layoutManager = LinearLayoutManager(requireActivity())
            it.itemAnimator = DefaultItemAnimator()
            it.setHasFixedSize(true)
        }
        setupMessagesErrorAndProgressBar()
        return root
    }

    override fun onResume() {
        super.onResume()
        taskCategories()
    }

    private fun taskCategories() {

        val categories = mutableListOf<String>()

        with(viewModel) {

            taskCategories()

            showCategories.observe(viewLifecycleOwner, Observer { response ->

                if (response.isNotEmpty())
                    if (categories.isEmpty()) {

                        categories.addAll(response)

                        recyclerView.adapter = CategoriasAdapter(categories) { categoria: String ->
                            Toast.makeText(context, "Detalhe not implemented yet", Toast.LENGTH_SHORT).show()
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
                    if (it == true) context?.toast("Sem conexão com a internet!\nTente novamente daqui a pouco...")
                }
            })

            showMessageServerError.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled().let {
                    if (it == true) context?.toast("Erro no servidor interno! Tente novamente daqui a pouco...")
                }
            })

        }

    }

}