package com.example.chuckeinstein.ui.piadas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chuckeinstein.R
import com.example.chuckeinstein.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ListaCategoriasFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: PiadasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.lista_categorias_fragment, container, false)
        AndroidSupportInjection.inject(this)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PiadasViewModel::class.java)
        mostrarPiada(viewModel.pegarTextoRepositorio())
    }

    private fun mostrarPiada(piada: String) {
        val acao = ListaCategoriasFragmentDirections.actionMainFragmentToPiadaFragment(piada)
        findNavController().navigate(acao)
    }

}